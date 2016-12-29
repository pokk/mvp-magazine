package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.PopularMovies
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class MoviePopularPresenter constructor(val moviesCase: PopularMovies):
        BasePresenter<MoviePopularContract.View>(), MoviePopularContract.Presenter {
    //region Subscribers
    private val popularMovieSub = subscriber<List<MovieBriefModel>>().onCompleted {
        AppLog.d()
    }.onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.v(it)
        view.finishLoadingMovie(it)
    }

    //endregion

    //region View implementation
    override fun init(view: MoviePopularContract.View) {
        super.init(view)

        val request = PopularMovies.Requests(1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.popularMovieSub)
    }
    //endregion
}
