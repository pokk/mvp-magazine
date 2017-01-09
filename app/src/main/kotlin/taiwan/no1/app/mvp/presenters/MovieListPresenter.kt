package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.MovieListContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   12/6/16
 */

class MovieListPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {
    //region Subscribers
    private val popularMovieSub = subscriber<List<MovieBriefModel>>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        view.obtainMovieBriefList(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MovieListContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestListMovies(category: DataRepository.Movies, page: Int) {
        val request = MovieLists.Requests(category, page)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.popularMovieSub)
    }
}
