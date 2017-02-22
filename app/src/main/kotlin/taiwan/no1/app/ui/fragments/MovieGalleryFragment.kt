package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.transition.TransitionInflater
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
import taiwan.no1.app.mvp.contracts.fragment.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageProfileModel
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
        fun newInstance(movies: List<ImageProfileModel>):
                MovieGalleryFragment = MovieGalleryFragment().apply {
            this.arguments = Bundle().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionInflater.from(App.getAppContext()).let {
                        sharedElementEnterTransition = it.inflateTransition(R.transition.change_image_transform)
                    }
                }
                this.putParcelableArray(ARG_PARAM_IMAGES, movies.toTypedArray())
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
    // Get the arguments from the bundle here.
    private val argMovieImages: List<ImageProfileModel> by lazy {
        this.arguments.getParcelableArray(ARG_PARAM_IMAGES).toList() as List<ImageProfileModel>
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
        this.getComponent(FragmentComponent::class.java).inject(MovieGalleryFragment@ this)
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
        // FIXED: 2/1/17 Used [ImageSwitcher] to fix the animation changing of the backgrounds smoothly.
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
        this.presenter.updatePosters(this.argMovieImages)
        this.presenter.updatePageOfNumber()
    }

    override fun showPosters(moviePosters: List<ImageProfileModel>) {
        this.hicvpGallery.apply {
            this@MovieGalleryFragment.presenter.updateOldItemIndex(this.currentItem)
            this.adapter = HorizontalPagerAdapter(this.context, false, moviePosters)
            // Set the current blur image in viewpager's background.
            // FIXED: 2/1/17 Sometimes the page's been selected but the present item is still not changed.
            // FIXED: So I'm using finding the item's specific tag to fix it.
            this.pageSelections().compose(this@MovieGalleryFragment.bindToLifecycle<Int>()).subscribe {
                this@MovieGalleryFragment.presenter.attachBackgroundFrom(this)
            }
        }
    }

    override fun showCurrentNumOfPosters(total: String) {
        this.tvNumbers.text = total
    }
    //endregion

    //region View implementation
    override fun showBlurBackground(image: Bitmap) {
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
        this.presenter.updateISAspectRatio(this.isBackground.width.toDouble() / this.isBackground.height.toDouble())
        this.presenter.updateIsFirstImg(true)
        this.presenter.resizeImageToFitBackground(Bitmap.createBitmap(this.presenter.extractBitmap(this.hicvpGallery,
                this.hicvpGallery.realItem)))
    }

    /**
     * When scrolling to the photo isn't finished. And after the photo is finished loading.
     */
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_FINISHED_LOADING_IMG)))
    fun finishedLoadingImage(msg: String) {
        this.presenter.attachBackgroundFrom(this.hicvpGallery)
    }
    //endregion
}
