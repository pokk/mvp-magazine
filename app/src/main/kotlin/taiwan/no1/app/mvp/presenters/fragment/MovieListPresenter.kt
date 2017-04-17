package taiwan.no1.app.mvp.presenters.fragment

import com.devrapid.kotlinknifer.AppLog
import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.mvp.contracts.fragment.MovieListContract
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   12/6/16
 */

class MovieListPresenter constructor(val moviesCase: MovieLists):
        BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {
    private var movieBriefModelList: List<MovieBriefModel> = emptyList()

    //region Presenter implementation
    override fun init(view: MovieListContract.View) {
        super.init(view)
    }

    override fun requestListMovies(category: CloudDataStore.Movies, page: Int) {
        val request = MovieLists.Requests(category, page)
        request.fragmentLifecycle = this.view.getLifecycle()
        // TODO: 4/4/17 Create a customize subscriber in [BasePresenter].
        // If declaring [subscriber] as a variable, it won't be used again.
        this.moviesCase.execute(request, subscriber<List<MovieBriefModel>>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
            this.view.showRetry()
        }.onNext {
            this.movieBriefModelList += it
            view.showMovieBriefList(this.movieBriefModelList)
        }.onCompleted { this.view.hideLoading() })
    }

    override fun restoreMovieList(movieList: List<MovieBriefModel>) {
        this.movieBriefModelList = movieList.toList()
    }

    override fun getMovieList(): ArrayList<MovieBriefModel> = ArrayList(this.movieBriefModelList)
    //endregion
}
