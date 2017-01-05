package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hwangjr.rxbus.RxBus
import com.intrusoft.squint.DiagonalView
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieDetailModel
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.MovieCastsAdapter
import taiwan.no1.app.ui.adapter.MovieCrewsAdapter
import taiwan.no1.app.ui.adapter.MovieRelatedAdapter
import taiwan.no1.app.ui.adapter.MovieTrailerAdapter
import taiwan.no1.app.ui.itemdecorator.MovieHorizontalItemDecorator
import javax.inject.Inject


/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/29/16
 */

@PerFragment
class MovieDetailFragment: BaseFragment(), MovieDetailContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_MOVIE_ID: String = "param_movie_id"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieDetailFragment.
         */
        fun newInstance(id: String): MovieDetailFragment {
            val fragment: MovieDetailFragment = MovieDetailFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_PARAM_MOVIE_ID, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    private val ivDropPoster by bindView<DiagonalView>(R.id.dv_poster)
    private val ivMoviePoster by bindView<ImageView>(R.id.iv_movie_poster)
    private val tvReleaseDate by bindView<TextView>(R.id.tv_release_date)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val stubIntro by bindView<ViewStub>(R.id.stub_introduction)
    private val stubCasts by bindView<ViewStub>(R.id.stub_casts)
    private val stubCrews by bindView<ViewStub>(R.id.stub_crews)
    private val stubRelated by bindView<ViewStub>(R.id.stub_related)
    private val stubTrailer by bindView<ViewStub>(R.id.stub_trailer)
    private val tvOverview by bindView<TextView>(R.id.tv_overview)
    private val tvStatus by bindView<TextView>(R.id.tv_status)
    private val tvRunTime by bindView<TextView>(R.id.tv_run_time)
    private val tvLanguage by bindView<TextView>(R.id.tv_language)
    private val tvProduction by bindView<TextView>(R.id.tv_productions)
    private val tvVote by bindView<TextView>(R.id.tv_vote)
    private val rvCasts by bindView<RecyclerView>(R.id.rv_casts)
    private val rvCrews by bindView<RecyclerView>(R.id.rv_crews)
    private val rvRelated by bindView<RecyclerView>(R.id.rv_related)
    private val rvTrailer by bindView<RecyclerView>(R.id.rv_trailer)

    // The fragment initialization parameters.
    private var argMovieId: String? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        this.argMovieId = arguments?.getString(MovieDetailFragment.ARG_PARAM_MOVIE_ID)
    }

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
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(MovieDetailFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        this.argMovieId?.toInt()?.let {
            this.presenter.requestMovieDetail(it)
        }
        this.ivDropPoster.setOnClickListener {
            RxBus.get().post(RxbusTag.FRAGMENT_NAVIGATOR,
                    MovieGalleryFragment.newInstance(""))
        }
    }

    //endregion

    override fun showMovieDetail(movieDetailModel: MovieDetailModel) {
        // Inflate the introduction section.
        if (null != stubIntro.parent) {
            stubIntro.inflate()
            Glide.with(this.context.applicationContext).
                    load(MovieDBConfig.BASAE_IMAGE_URL + movieDetailModel.backdrop_path).
                    fitCenter().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(this.ivDropPoster)
            Glide.with(this.context.applicationContext).
                    load(MovieDBConfig.BASAE_IMAGE_URL + movieDetailModel.poster_path).
                    fitCenter().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(this.ivMoviePoster)
            this.tvReleaseDate.text = movieDetailModel.release_date
            this.tvTitle.text = movieDetailModel.title
            this.tvOverview.text = movieDetailModel.overview
            this.tvStatus.text = movieDetailModel.status
            this.tvRunTime.text = movieDetailModel.runtime.toString()
            this.tvLanguage.text = movieDetailModel.original_language
            movieDetailModel.production_countries?.let {
                var productions = ""
                for ((iso, name) in it)
                    productions += name
                this.tvProduction.text = productions
            }
            this.tvVote.text = movieDetailModel.vote_average.toString()
        }
        else
            stubIntro.visibility = View.VISIBLE

        // Inflate the cast section.
        if (null != stubCasts.parent)
            movieDetailModel.casts?.cast?.let {
                stubCasts.inflate()
                this.showMovieCasts(it)
            }
        else
            stubCasts.visibility = View.VISIBLE

        // Inflate the crew section.
        if (null != stubCrews.parent)
            movieDetailModel.casts?.crew?.let {
                stubCrews.inflate()
                this.showMovieCrews(it)
            }
        else
            stubCrews.visibility = View.VISIBLE


        // Inflate the related movies section.
        if (null != stubRelated.parent)
            movieDetailModel.similar?.movieBriefModel?.let {
                stubRelated.inflate()
                this.showSimilarMovies(it)
            }
        else
            stubRelated.visibility = View.VISIBLE

        // Inflate the trailer movies section.
        if (null != stubTrailer.parent)
            movieDetailModel.videos?.results?.let {
                stubTrailer.inflate()
                this.showTrailerMovies(it)
            }
        else
            stubTrailer.visibility = View.VISIBLE
    }

    private fun showMovieCasts(castList: List<MovieCastsModel.CastBean>) {
        this.rvCasts.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvCasts.adapter = MovieCastsAdapter(this.context, castList.filter { null != it.profile_path })
        this.rvCasts.addItemDecoration(MovieHorizontalItemDecorator(20))
    }

    private fun showMovieCrews(crewList: List<MovieCastsModel.CrewBean>) {
        this.rvCrews.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvCrews.adapter = MovieCrewsAdapter(this.context, crewList.filter { null != it.profile_path })
        this.rvCrews.addItemDecoration(MovieHorizontalItemDecorator(20))
    }

    private fun showSimilarMovies(similarMovieList: List<MovieBriefModel>) {
        this.rvRelated.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvRelated.adapter = MovieRelatedAdapter(this.context, similarMovieList)
        this.rvRelated.addItemDecoration(MovieHorizontalItemDecorator(20))
    }

    private fun showTrailerMovies(videoMovieList: List<MovieVideosModel>) {
        this.rvTrailer.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvTrailer.adapter = MovieTrailerAdapter(this.context, videoMovieList)
        this.rvTrailer.addItemDecoration(MovieHorizontalItemDecorator(20))
    }
}
