package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.jakewharton.rxbinding.support.v4.view.pageSelections
import com.touchin.constant.RxbusTag
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_gallery.*
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.HorizontalPagerAdapter
import javax.inject.Inject


/**
 * Show the poster photos of the movies and the actors [BaseFragment].
 *
 * @author  Jieyi
 * @since   1/1/17
 */

@PerFragment
class MovieGalleryFragment: BaseFragment(), MovieGalleryContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_IMAGES: String = "param_images"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieGalleryFragment.
         */
        fun newInstance(movies: List<ImageInfoModel>? = null):
                MovieGalleryFragment = MovieGalleryFragment().apply {
            this.arguments = Bundle().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionInflater.from(App.getAppContext()).let {
                        sharedElementEnterTransition = it.inflateTransition(R.transition.change_image_transform)
                    }
                }
                this.putParcelableArray(ARG_PARAM_IMAGES, movies?.toTypedArray())
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: MovieGalleryContract.Presenter

    //region View variables
    private val hicvpGallery by bindView<HorizontalInfiniteCycleViewPager>(R.id.hicvp_gallery)
    private val isBackground by bindView<ImageSwitcher>(R.id.is_gallery_background)
    private val tvNumbers by bindView<TextView>(R.id.tv_numbers)
    //endregion

    //region Local variables
    private var isFirstImageFinished: Boolean = false

    // Get the arguments from the bundle here.
    private val argMovieImages: List<ImageInfoModel>? by lazy {
        this.arguments.getParcelableArray(ARG_PARAM_IMAGES).toList() as List<ImageInfoModel>
    }
    private val aspectRatio: Double by lazy {
        this.isBackground.width.toDouble() / this.isBackground.height.toDouble()
    }
    //endregion

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register RxBus.
        RxBus.get().register(this)
    }

    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // Unregister RxBus.
        RxBus.get().unregister(this)
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java, null).inject(MovieGalleryFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_gallery

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(MovieGalleryFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        val total: Int = this.argMovieImages?.size ?: 0

        // FIXED: 2/1/17 Used [ImageSwitcher] to fix the animation changing of the backgrounds.
        this.isBackground.apply {
            // Create the inner image view by factory pattern.
            this.setFactory {
                ImageView(MovieGalleryFragment@ this.context).apply {
                    // Assign the layout is match the parent.
                    this.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT)
                }
            }
            // Set the changing images' animation.
            this.inAnimation = AnimationUtils.loadAnimation(MovieGalleryFragment@ this.context, android.R.anim.fade_in)
            this.outAnimation = AnimationUtils.loadAnimation(MovieGalleryFragment@ this.context,
                    android.R.anim.fade_out)
        }
        this.tvNumbers.text = this.setNumberText(total)
        this.hicvpGallery.apply {
            var oldItemIndex: Int = this.currentItem
            this.adapter = argMovieImages?.let { HorizontalPagerAdapter(this.context, false, it) }
            // Set the current blur image in viewpager's background.
            // FIXED: 2/1/17 Sometimes the page's been selected but the present item is still not changed.
            // So I'm using finding the item's specific tag to fix it.
            this.pageSelections().subscribe { attachBackground() }
        }
    }
    //endregion

    fun attachBackground() {
        val total: Int = this.argMovieImages?.size ?: 0
        var oldItemIndex: Int = this.hicvpGallery.currentItem

        if (isFirstImageFinished) {
            val presentItem: Bitmap? = extractBitmapFromItem(this.hicvpGallery.realItem)
            if (null == presentItem) {
                // TODO: 2017/02/10 ##### There might have a better way to fix this problem.
                // FIXED: 2017/02/10 Fixed way as below... Scroll -> attachBgd(Fragment) -> (img is null) ->
                // FIXED: Notify(PagerAdapter) -> Finished loading -> attachBgd(Fragment) again.
                (this.hicvpGallery.adapter as HorizontalPagerAdapter).notifyNotLoadYet = true
            }
            else {
                tvNumbers.text = setNumberText(total, this.hicvpGallery.realItem + 1)
                presenter.resizeImageToFitBackground(aspectRatio, Bitmap.createBitmap(presentItem))
                oldItemIndex = this.hicvpGallery.currentItem
            }
        }
    }

    //region View implementation
    override fun setBlurBackground(image: Bitmap) {
        Blurry.with(this.context).radius(20).sampling(4).async({
            isBackground.setImageDrawable(it as Drawable)
        })./* Here are redundant code, but it won't work without them. */from(image).into(iv_hidden)
    }
    //endregion

    //region RxBus
    /**
     * Entry the gallery view after the first photo was finished loading, doing this method to resize the photo size.
     */
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_FINISHED_FIRST_IMG)))
    fun finishedFirstLoadImg(msg: String) {
        this.isFirstImageFinished = true
        this.presenter.resizeImageToFitBackground(this.aspectRatio,
                Bitmap.createBitmap(this.extractBitmapFromItem(hicvpGallery.realItem)))
    }

    /**
     * When scrolling to the photo isn't finished. And after the photo is finished loading.
     */
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_FINISHED_LOADING_IMG)))
    fun finishedLoadingImage(msg: String) {
        attachBackground()
    }
    //endregion

    /**
     * Search the specific view item from the view pager.
     *
     * @param index index.
     * @return [View]
     */
    private fun findViewPagerItem(index: Int): View? {
        (0..this.hicvpGallery.childCount - 1).forEach {
            if (index == hicvpGallery.getChildAt(it).tag)
                return hicvpGallery.getChildAt(it)
        }

        return null
    }

    // FIXED: 2017/02/10 Changed the Glide listener to BitmapImageViewTarget then we can check the drawable type as well.
    private fun extractBitmapFromItem(index: Int): Bitmap? =
            ((findViewPagerItem(index)?.findViewById(R.id.img_item) as ImageView).drawable as? BitmapDrawable)?.bitmap

    private fun setNumberText(totalNumber: Int, currentNumber: Int = 1) = "$currentNumber / $totalNumber"
}
