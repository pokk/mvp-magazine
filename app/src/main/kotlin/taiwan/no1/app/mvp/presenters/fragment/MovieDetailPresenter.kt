package taiwan.no1.app.mvp.presenters.fragment

import android.view.View
import com.hwangjr.rxbus.RxBus
import com.intrusoft.squint.DiagonalView
import com.touchin.constant.RxbusTag
import rx.lang.kotlin.subscriber
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.mvp.contracts.fragment.MovieDetailContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.mvp.models.movie.MovieDetailModel
import taiwan.no1.app.ui.fragments.MovieGalleryFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment
import taiwan.no1.app.utilies.AppLog
import taiwan.no1.app.utilies.TimeUtils

/**
 *
 * @author  Jieyi
 * @since   12/29/16
 */

class MovieDetailPresenter constructor(val movieDetailCase: MovieDetail):
        BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {
    private var movieDetailModel: MovieDetailModel? = null

    //region Subscribers
    private val movieDetailSub = subscriber<MovieDetailModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
        this.view.showRetry()
    }.onNext {
        // TODO: 2/19/17 Here might be memory leak!?
        this.movieDetailModel = it

        this.movieDetailModel?.let {
            val runtime: TimeUtils.DateTime = TimeUtils.number2Time(it.runtime.toDouble(), TimeUtils.TimeType.Min)

            this.view.showMovieBackdrops(this.createViewPagerViews(it.images?.backdrops.orEmpty()))
            this.view.showMovieCover(TMDBConfig.BASE_IMAGE_URL + it.poster_path)
            this.view.showMovieBase(it.title.orEmpty(),
                    it.release_date.orEmpty(),
                    "  ${runtime.hours} h ${runtime.mins} m",
                    it.vote_average / 2)
            this.view.showMovieDetail(it.overview.orEmpty(),
                    it.status.orEmpty(),
                    it.original_language.orEmpty(),
                    it.production_countries?.let { it.flatMap { listOf(it.name) }.joinToString("") }.orEmpty())
            this.view.showMovieCasts(it.casts?.cast?.filter { null != it.profile_path }.orEmpty())
            this.view.showMovieCrews(it.casts?.crew?.filter { null != it.profile_path }.orEmpty())
            this.view.showRelatedMovies(it.similar?.movieBriefModel?.let {
                it.map { it.apply { it.isMainView = false } }.sortedWith(compareBy({ it.release_date })).reversed()
            }.orEmpty())
            this.view.showMovieTrailers(it.videos?.results.orEmpty())
        }
    }.onCompleted { this.view.hideLoading() }
    //endregion

    //region Presenter implementation
    override fun init(view: MovieDetailContract.View) {
        super.init(view)
    }

    override fun requestMovieDetail(movieId: Int) {
        this.view.showLoading()
        
        val request = MovieDetail.Requests(movieId)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.movieDetailCase.execute(request, this.movieDetailSub)
    }

    override fun onResourceFinished(view: View, tag: Int) {
        view.setOnClickListener {
            val posters: List<ImageProfileModel> = this.movieDetailModel?.let {
                it.images?.posters?.filter { "en" == it.iso_639_1 }
            } ?: emptyList()

            if (posters.isNotEmpty())
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_FRAGMENT,
                                MovieGalleryFragment.newInstance(posters)),
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_TAG, tag)))
        }
    }
    //endregion

    /**
     * Creating a view list for view pager shows each of the view.
     *
     * @param backdrops backdrops' data.
     * @return a view of list.
     */
    private fun createViewPagerViews(backdrops: List<ImageProfileModel>): List<View> =
            backdrops.map {
                // FIXME: 3/28/17 當還在讀取中，突然轉去其他頁面，context會變成null.
                View.inflate(this.view.context(), R.layout.item_movie_backdrop, null) as DiagonalView
            }.also {
                it.forEachIndexed { i, diagonalView ->
                    this.view.showMovieSingleBackdrop(TMDBConfig.BASE_IMAGE_URL + backdrops[i].file_path, diagonalView)
                }
            }
}
