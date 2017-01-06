package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.MovieTopRatedContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/5/17
 */
class MovieTopRatedPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MovieTopRatedContract.View>(), MovieTopRatedContract.Presenter {
    //region Subscribers
    private val topRatedMovieSub = subscriber<List<MovieBriefModel>>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.v(it)
        this.view.obtainMovieBriefList(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MovieTopRatedContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestTopRatedMovies() {
        val request = MovieLists.Requests(DataRepository.Movies.TOP_RATED, 1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.topRatedMovieSub)
    }
}
