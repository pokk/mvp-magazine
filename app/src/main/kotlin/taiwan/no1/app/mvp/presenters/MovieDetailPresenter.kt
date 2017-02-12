package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.MovieDetailModel
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
        this.movieDetailInfo = it

        this.movieDetailInfo?.let {
            val runtime: TimeUtils.DateTime = TimeUtils.number2Time(it.runtime.toDouble(), TimeUtils.TimeType.Min)

            this.view.showMoviePosters(it.images?.backdrops ?: emptyList(),
                    it.images?.posters?.filter { "en" == it.iso_639_1 } ?: emptyList())
            this.view.showMovieCover(MovieDBConfig.BASE_IMAGE_URL + it.poster_path)
            this.view.showMovieBase(it.title ?: "",
                    it.release_date ?: "",
                    "  ${runtime.hours} h ${runtime.mins} m",
                    it.vote_average / 2)
            this.view.showMovieDetail(it.overview ?: "",
                    it.status ?: "",
                    it.original_language ?: "",
                    it.production_countries?.let { it.flatMap { listOf(it.name) }.joinToString("") } ?: "")
            this.view.showMovieCasts(it.casts?.cast?.filter { null != it.profile_path } ?: emptyList())
            this.view.showMovieCrews(it.casts?.crew?.filter { null != it.profile_path } ?: emptyList())
            this.view.showRelatedMovies(it.similar?.movieBriefModel?.let {
                it.map { it.apply { it.isMainView = false } }.sortedWith(compareBy({ it.release_date })).reversed()
            } ?: emptyList())
            this.view.showMovieTrailers(it.videos?.results ?: emptyList())
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
