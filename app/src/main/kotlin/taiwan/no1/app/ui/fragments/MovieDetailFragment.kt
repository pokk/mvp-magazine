package taiwan.no1.app.ui.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.models.MovieDetailModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.DropMoviePagerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.ui.customize.StarScoreView
import taiwan.no1.app.utilies.AppLog
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import taiwan.no1.app.utilies.TimeUtils
import javax.inject.Inject
import kotlin.comparisons.compareBy


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
//                TransitionInflater.from(App.getAppContext()).let {
//                    this.sharedElementReturnTransition = it.inflateTransition(R.transition.change_image_transform)
//                    this.exitTransition = it.inflateTransition(android.R.transition.fade)
//                    this.sharedElementEnterTransition = it.inflateTransition(R.transition.change_image_transform)
//                    this.enterTransition = it.inflateTransition(android.R.transition.fade)
//                    this.reenterTransition = it.inflateTransition(android.R.transition.fade)
//                }
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

    private var movieDetail: MovieDetailModel? = null
    // Get the arguments from the bundle here.
    private val argMovieId: String by lazy { this.arguments.getString(ARG_PARAM_MOVIE_ID) }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_FROM_ID) }

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
        super.onDestroy()
    }
//endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java, null).inject(MovieDetailFragment@ this)
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
    override fun showMovieDetail(movieDetailModel: MovieDetailModel) {
        // TODO: 1/8/17 Here may happen memory leak!? We need to use deep copy.
        this.movieDetail = movieDetailModel

        AppLog.w(movieDetailModel.id)
        this.vpDropPoster.adapter = movieDetailModel.images?.let {
            if (null != it.backdrops && null != it.posters)
                DropMoviePagerAdapter(this.context,
                        it.backdrops,
                        it.posters.filter { "en" == it.iso_639_1 },
                        argFromFragment)
            else
                null
        }
        this.imageLoader.display(MovieDBConfig.BASE_IMAGE_URL + movieDetailModel.poster_path, this.ivMoviePoster)
        this.tvReleaseDate.setBackgroundColor(Color.TRANSPARENT)
        this.tvReleaseDate.text = movieDetailModel.release_date
        this.tvTitle.setBackgroundColor(Color.TRANSPARENT)
        this.tvTitle.text = movieDetailModel.title
        TimeUtils.number2Time(movieDetailModel.runtime.toDouble(), TimeUtils.TimeType.Min).let {
            this.tvTime.text = "  ${it.hours} h ${it.mins} m"
        }
        this.ssvStarRate.score = movieDetailModel.vote_average / 2

        // Inflate the introduction section.
        this.showViewStub(this.stubIntro, {
            this.tvOverview.text = movieDetailModel.overview
            this.tvStatus.text = movieDetailModel.status
            this.tvLanguage.text = movieDetailModel.original_language
            movieDetailModel.production_countries?.let {
                this.tvProduction.text = it.flatMap { listOf(it.name) }.joinToString("")
            }
        })

        // Inflate the cast section.
        this.showViewStub(this.stubCasts, {
            movieDetailModel.casts?.cast?.let {
                this.showCardItems(this.rvCasts,
                        it.filter { null != it.profile_path })
            }
        })

        // Inflate the crew section.
        this.showViewStub(this.stubCrews, {
            movieDetailModel.casts?.crew?.let {
                this.showCardItems(this.rvCrews, it.filter { null != it.profile_path })
            }
        })

        // Inflate the related movieList section.
        this.showViewStub(this.stubRelated, {
            movieDetailModel.similar?.movieBriefModel?.let {
                this.showCardItems(this.rvRelated, it.map {
                    it.apply { it.isMainView = false }
                }.sortedWith(compareBy({ it.release_date })).reversed())
            }
        })

        // Inflate the trailer movieList section.
        this.showViewStub(this.stubTrailer, {
            movieDetailModel.videos?.results?.let { this.showCardItems(this.rvTrailer, it) }
        })
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
