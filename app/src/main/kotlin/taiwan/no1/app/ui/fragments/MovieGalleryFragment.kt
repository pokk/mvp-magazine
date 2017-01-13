package taiwan.no1.app.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager.SCROLL_STATE_IDLE
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.bindView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.jakewharton.rxbinding.support.v4.view.pageScrollStateChanges
import jp.wasabeef.blurry.Blurry
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.HorizontalPagerAdapter
import javax.inject.Inject

/**
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
                this.putParcelableArray(ARG_PARAM_IMAGES, movies?.toTypedArray())
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: MovieGalleryContract.Presenter

    //region View variables
    private val hicvpGallery by bindView<HorizontalInfiniteCycleViewPager>(R.id.hicvp_gallery)
    private val ivBackground by bindView<ImageView>(R.id.iv_gallery_background)
    //endregion

    // Get the arguments from the bundle here.
    private val argMovieImages: List<ImageInfoModel>? by lazy {
        this.arguments.getParcelableArray(ARG_PARAM_IMAGES).toList() as List<ImageInfoModel>
    }

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
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
        var currPosition: Int = 0
        var prevPosition: Int = -1

        // FIXME: 2017/01/12 Make the card frame to fit the image size.
//        this.hicvpGallery.viewTreeObserver.addOnGlobalLayoutListener {
//            (this.hicvpGallery.adapter as HorizontalPagerAdapter).itemHeight = this.hicvpGallery.height
//            (this.hicvpGallery.adapter as HorizontalPagerAdapter).itemWidth = this.hicvpGallery.width
//        }
        this.hicvpGallery.apply {
            this.adapter = argMovieImages?.let {
                HorizontalPagerAdapter(this.context, false, ivBackground, it)
            }
            // Set the current blur image in viewpager's background.
            this.pageScrollStateChanges().subscribe {
                when (it) {
                    SCROLL_STATE_IDLE -> {
                        // Avoiding to slide at the same page then setting the same image many times. So keeping
                        // the previous position and the current position.
                        currPosition = hicvpGallery.currentItem
                        if (currPosition != prevPosition) {
                            ((hicvpGallery.getChildAt(hicvpGallery.childCount - 1) as ViewGroup).
                                    findViewById(R.id.img_item) as ImageView).let {
                                // FIXME: 2017/01/13 The first image won't be showed.
                                it.drawable?.let {
                                    Blurry.with(this.context).
                                            radius(25).
                                            from((it as BitmapDrawable).bitmap).
                                            into(ivBackground)
                                }
                            }
                        }
                        prevPosition = currPosition
                    }
                }
            }
        }
//        (this.hicvpGallery.adapter as HorizontalPagerAdapter).itemHeight = this.hicvpGallery.height
//        (this.hicvpGallery.adapter as HorizontalPagerAdapter).itemWidth = this.hicvpGallery.width
    }
    //endregion
}
