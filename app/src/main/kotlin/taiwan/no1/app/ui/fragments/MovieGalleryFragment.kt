package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import butterknife.bindView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieGalleryContract
import taiwan.no1.app.mvp.models.CastImagesModel
import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.mvp.models.MovieImagesModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.HorizontalPagerAdapter
import taiwan.no1.app.utilies.AppLog
import java.util.*
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
        private val ARG_PARAM_MOVIE_IMAGES: String = "param_movie_images"
        private val ARG_PARAM_CAST_IMAGES: String = "param_cast_images"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieGalleryFragment.
         */
        fun newInstance(movies: MovieImagesModel? = null, casts: CastImagesModel? = null):
                MovieGalleryFragment = MovieGalleryFragment().apply {
            this.arguments = Bundle().apply {
                this.putParcelable(ARG_PARAM_MOVIE_IMAGES, movies)
                this.putParcelable(ARG_PARAM_CAST_IMAGES, casts)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: MovieGalleryContract.Presenter

    //region View variables
    private val hicvpGallery by bindView<HorizontalInfiniteCycleViewPager>(R.id.hicvp_gallery)
    //endregion

    // Get the arguments from the bundle here.
    private val argMovieImages: MovieImagesModel? by lazy {
        this.arguments.getParcelable<MovieImagesModel>(ARG_PARAM_MOVIE_IMAGES)
    }
    private val argCastImages: CastImagesModel? by lazy {
        this.arguments.getParcelable<CastImagesModel>(ARG_PARAM_CAST_IMAGES)
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
        AppLog.v(this.argMovieImages)
        val lists: List<ImageInfoModel> = (this.argMovieImages?.let {
            (it.posters?.filter { it.iso_639_1 == "en" } ?: ArrayList()) + (it.backdrops ?: ArrayList())
        } ?: this.argCastImages?.let(CastImagesModel::profiles))
                ?: ArrayList()

        this.hicvpGallery.adapter = HorizontalPagerAdapter(this.context, false, lists)
    }
    //endregion
}
