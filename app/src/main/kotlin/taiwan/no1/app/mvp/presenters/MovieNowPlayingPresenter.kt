package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.MovieNowPlayingContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/5/17
 */
class MovieNowPlayingPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MovieNowPlayingContract.View>(), MovieNowPlayingContract.Presenter {
    //region Subscribers
    private val nowPlayingMovieSub = subscriber<List<MovieBriefModel>>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.v(it)
        this.view.obtainMovieBriefList(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MovieNowPlayingContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestNowPlayingMovies() {
        val request = MovieLists.Requests(DataRepository.Movies.NOW_PLAYING, 1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.nowPlayingMovieSub)
    }
}
