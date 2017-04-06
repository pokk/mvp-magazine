package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.CardView
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.jakewharton.rxbinding.support.v4.view.pageSelections
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.BackdropPagerAdapter
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
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
         * @return A new instance of [MovieGalleryFragment].
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
    @Inject
    lateinit var imageLoader: IImageLoader

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
            this.inAnimation = AnimationUtils.loadAnimation(this@MovieGalleryFragment.context, android.R.anim.fade_in)
            this.outAnimation = AnimationUtils.loadAnimation(this@MovieGalleryFragment.context, android.R.anim.fade_out)
        }
        this.presenter.updatePosters(this.argMovieImages)
        this.presenter.updatePageOfNumber()
    }

    override fun showPosters(viewList: List<View>) {
        this.hicvpGallery.apply {
            this.adapter = BackdropPagerAdapter(viewList)
            this@MovieGalleryFragment.presenter.updateOldItemIndex(this.currentItem)
            // FIXED: 2/1/17 Sometimes the page's been selected but the present item is still not changed.
            // FIXED: So I'm using finding the item's specific tag to fix it.
            this.pageSelections().compose(this@MovieGalleryFragment.bindToLifecycle<Int>()).subscribe {
                val isRatio: Double = this@MovieGalleryFragment.isBackground.let {
                    it.width.toDouble() / it.height.toDouble()
                }
                // Set the current blur image in viewpager's background.
                this@MovieGalleryFragment.presenter.attachBackgroundFrom(this, isRatio)
            }
        }
    }

    override fun showSinglePoster(uri: String, position: Int, imageView: ImageView, cvFrame: CardView) {
        this.imageLoader.display(uri, listener = object: GlideResizeTargetListener(imageView, cvFrame) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                super.onResourceReady(resource, glideAnimation)

                val isRatio: Double = this@MovieGalleryFragment.isBackground.let {
                    it.width.toDouble() / it.height.toDouble()
                }
                this@MovieGalleryFragment.presenter.onResourceFinished(this@MovieGalleryFragment.hicvpGallery,
                        isRatio, position)
            }
        })
    }

    override fun showCurrentNumOfPosters(total: String) {
        this.tvNumbers.text = total
    }
    //endregion

    //region View implementation
    override fun showBlurBackground(image: Bitmap) {
        // FIXED: 3/11/17 http://stackoverflow.com/questions/10919240/fragment-myfragment-not-attached-to-activity
        if (this.isAdded)
            this.isBackground.setImageDrawable(BitmapDrawable(resources, image))
    }
    //endregion
}
