package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.movie.MovieDetailModel
import taiwan.no1.app.utilies.AppLog
import taiwan.no1.app.utilies.TimeUtils
import kotlin.comparisons.compareBy

/**
 *
 * @author  Jieyi
 * @since   12/29/16
 */

class MovieDetailPresenter constructor(val movieDetailCase: MovieDetail):
        BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {
    private var movieDetailInfo: MovieDetailModel? = null

    //region Subscribers
    private val movieDetailSub = subscriber<MovieDetailModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        // TODO: 2/19/17 Here might be memory leak!?
        this.movieDetailInfo = it

        this.movieDetailInfo?.let {
            val runtime: TimeUtils.DateTime = TimeUtils.number2Time(it.runtime.toDouble(), TimeUtils.TimeType.Min)

            this.view.showMoviePosters(it.images?.backdrops.orEmpty(),
                    it.images?.posters?.filter { "en" == it.iso_639_1 }.orEmpty())
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
    }
    //endregion

    //region Presenter implementation
    override fun init(view: MovieDetailContract.View) {
        super.init(view)
    }

    override fun requestMovieDetail(movieId: Int) {
        val request = MovieDetail.Requests(movieId)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.movieDetailCase.execute(request, this.movieDetailSub)
    }
    //endregion
}
