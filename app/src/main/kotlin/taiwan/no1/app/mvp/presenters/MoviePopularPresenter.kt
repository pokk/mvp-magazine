package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.GetPopularMovies
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.models.MovieModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class MoviePopularPresenter constructor(val moviesCase: GetPopularMovies):
        BasePresenter<MoviePopularContract.View>(), MoviePopularContract.Presenter {
    //region Subscribers
    private val fakeSubscriber = subscriber<List<MovieModel>>().onCompleted {
        AppLog.d()
    }.onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.v(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MoviePopularContract.View) {
        super.init(view)

//        val request = CreateFake.Requests(FakeModel("Jieyi", 19, "H"))
//        request.fragmentLifecycle = this.view.getLifecycle()
//        this.fakeCase.execute(request, this.fakeSubscriber)

        val request = GetPopularMovies.Requests(1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.fakeSubscriber)
    }
    //endregion
}
