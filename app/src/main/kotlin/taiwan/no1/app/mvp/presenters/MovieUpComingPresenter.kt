package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.MovieUpComingContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   1/5/17
 */
class MovieUpComingPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MovieUpComingContract.View>(), MovieUpComingContract.Presenter {
    //region Subscribers
    private val upComingMovieSub = subscriber<List<MovieBriefModel>>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.v(it)
        this.view.obtainMovieBriefList(it)
    }
    //endregion

    //region View implementation
    override fun init(view: MovieUpComingContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestUpComingMovies() {
        val request = MovieLists.Requests(DataRepository.Movies.UP_COMING, 1)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.moviesCase.execute(request, this.upComingMovieSub)
    }
}
