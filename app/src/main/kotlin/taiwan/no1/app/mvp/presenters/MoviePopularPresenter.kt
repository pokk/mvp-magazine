package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class MoviePopularPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MoviePopularContract.View>(), MoviePopularContract.Presenter {
    //region Subscribers
    private val popularMovieSub = subscriber<List<MovieBriefModel>>().onError {
        AppLog.e(it.message)
    }.onNext {
        AppLog.v(it)
        view.obtainMovieBriefList(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MoviePopularContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestPopularMovies() {
        val request = MovieLists.Requests(DataRepository.Movies.POPULAR, 1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.popularMovieSub)
    }
}
