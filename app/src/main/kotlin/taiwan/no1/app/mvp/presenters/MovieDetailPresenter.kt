package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.MovieCasts
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieDetailModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/29/16
 */

class MovieDetailPresenter constructor(val movieDetailCase: MovieDetail, val movieCastsCase: MovieCasts):
        BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {
    //region Subscribers
    private val movieDetailSub = subscriber<MovieDetailModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        view.obtainMovieDetail(it)
    }
    private val movieCastsSub = subscriber<MovieCastsModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        it.cast?.let { view.obtainMovieCasts(it) }
        it.crew?.let { view.obtainMovieCrews(it) }
    }
    //endregion

    //region View implementation
    override fun init(view: MovieDetailContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestMovieDetail(movieId: Int) {
        val request = MovieDetail.Requests(movieId)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.movieDetailCase.execute(request, this.movieDetailSub)
    }

    override fun requestMovieCasts(movieId: Int) {
        val request = MovieCasts.Requests(movieId)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.movieCastsCase.execute(request, this.movieCastsSub)
    }
}
