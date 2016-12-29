package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.models.MovieDetailModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/29/16
 */

class MovieDetailPresenter constructor(val movieDetailCase: MovieDetail):
        BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {
    //region Subscribers
    private val movieDetailSub = subscriber<MovieDetailModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        view.obtainMovieDetail(it)
        AppLog.v(it)
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
}
