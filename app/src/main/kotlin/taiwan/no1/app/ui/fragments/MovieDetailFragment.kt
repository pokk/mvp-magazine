package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.intrusoft.squint.DiagonalView
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.MovieDetailContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.BackdropPagerAdapter
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.ui.customize.StarScoreView
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject


/**
 *
 * @author  Jieyi
 * @since   12/29/16
 */

@PerFragment
class MovieDetailFragment: BaseFragment(), MovieDetailContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_MOVIE_ID: String = "param_movie_id"
        private const val ARG_PARAM_FROM_ID: String = "param_movie_from_fragment"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieDetailFragment.
         */
        fun newInstance(id: String, from: Int): MovieDetailFragment = MovieDetailFragment().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TransitionInflater.from(App.getAppContext()).let {
                    this.sharedElementReturnTransition = it.inflateTransition(R.transition.change_image_transform)
//                    this.exitTransition = it.inflateTransition(R.transition.detail_transform)
                    this.sharedElementEnterTransition = it.inflateTransition(R.transition.change_image_transform)
//                    this.enterTransition = it.inflateTransition(R.transition.detail_transform)
//                    this.reenterTransition = it.inflateTransition(R.transition.detail_transform)
                }
            }

            this.arguments = Bundle().apply {
                this.putString(ARG_PARAM_MOVIE_ID, id)
                this.putInt(ARG_PARAM_FROM_ID, from)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val vpDropPoster by bindView<ViewPager>(R.id.vp_drop_poster)
    private val ivMoviePoster by bindView<ImageView>(R.id.iv_movie_poster)
    private val tvReleaseDate by bindView<TextView>(R.id.tv_release_date)
    private val tvTime by bindView<TextView>(R.id.tv_run_time)
    private val ssvStarRate by bindView<StarScoreView>(R.id.ssv_score)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val stubIntro by bindView<ViewStub>(R.id.stub_introduction)
    private val stubCasts by bindView<ViewStub>(R.id.stub_casts)
    private val stubCrews by bindView<ViewStub>(R.id.stub_crews)
    private val stubRelated by bindView<ViewStub>(R.id.stub_related)
    private val stubTrailer by bindView<ViewStub>(R.id.stub_trailer)
    private val tvOverview by bindView<TextView>(R.id.tv_overview)
    private val tvStatus by bindView<TextView>(R.id.tv_status)
    private val tvLanguage by bindView<TextView>(R.id.tv_language)
    private val tvProduction by bindView<TextView>(R.id.tv_productions)
    private val rvCasts by bindView<RecyclerView>(R.id.rv_casts)
    private val rvCrews by bindView<RecyclerView>(R.id.rv_crews)
    private val rvRelated by bindView<RecyclerView>(R.id.rv_related)
    private val rvTrailer by bindView<RecyclerView>(R.id.rv_trailer)
    //endregion

    //region Local variables
    // Get the arguments from the bundle here.
    private val argMovieId: String by lazy { this.arguments.getString(ARG_PARAM_MOVIE_ID) }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_FROM_ID) }
    //endregion

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
//        this.stubIntro.inflate()
//        this.stubCasts.inflate()
//        this.stubCrews.inflate()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        this.rvTrailer.adapter?.let { (it as CommonRecyclerAdapter).releaseYouTubeLoader() }
        super.onDestroy()
    }
//endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java).inject(MovieDetailFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movie_detail

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(MovieDetailFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.argMovieId.toInt().let {
            this.presenter.requestMovieDetail(it)
        }
    }
    //endregion

    //region View implementations
    override fun showMovieBackdrops(viewList: List<View>) {
        this.vpDropPoster.adapter = BackdropPagerAdapter(viewList)
    }

    override fun showMovieSingleBackdrop(uri: String, diagonalView: DiagonalView) {
        this.imageLoader.display(uri, listener = object: BitmapImageViewTarget(diagonalView) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                diagonalView.solidColor = Color.TRANSPARENT
                this@MovieDetailFragment.presenter.onResourceFinished(diagonalView,
                        this@MovieDetailFragment.argFromFragment)
                super.onResourceReady(resource, glideAnimation)
            }
        }, isFitCenter = false)
    }

    override fun showMovieCover(posterUri: String) {
        this.imageLoader.display(posterUri, this.ivMoviePoster)
    }

    override fun showMovieBase(movieTitle: String, releaseDate: String, runtime: String, score: Double) {
        this.tvTitle.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.text = movieTitle
        }
        this.tvReleaseDate.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.text = releaseDate
        }
        this.tvTime.text = runtime
        this.ssvStarRate.score = score
    }

    override fun showMovieDetail(overview: String, status: String, languages: String, productions: String) {
        // Inflate the introduction section.
        this.showViewStub(this.stubIntro, {
            this.tvOverview.text = overview
            this.tvStatus.text = status
            this.tvLanguage.text = languages
            this.tvProduction.text = productions
        })
    }

    override fun showMovieCasts(casts: List<FilmCastsModel.CastBean>) {
        // Inflate the cast section.
        this.showViewStub(this.stubCasts, { this.showCardItems(this.rvCasts, casts) })
    }

    override fun showMovieCrews(crews: List<FilmCastsModel.CrewBean>) {
        // Inflate the crew section.
        this.showViewStub(this.stubCrews, { this.showCardItems(this.rvCrews, crews) })
    }

    override fun showRelatedMovies(relatedMovies: List<MovieBriefModel>) {
        // Inflate the related movieList section.
        this.showViewStub(this.stubRelated, { this.showCardItems(this.rvRelated, relatedMovies) })
    }

    override fun showMovieTrailers(trailers: List<FilmVideoModel>) {
        // Inflate the trailer movieList section.
        this.showViewStub(this.stubTrailer, { this.showCardItems(this.rvTrailer, trailers) })
    }
    //endregion

    private fun <T: IVisitable> showCardItems(recyclerView: RecyclerView, list: List<T>) {
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = CommonRecyclerAdapter(list, argFromFragment)
            this.addItemDecoration(MovieHorizontalItemDecorator(30))
        }
    }
}
