package taiwan.no1.app.mvp.contracts.fragment

import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView
import java.util.*

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   12/8/16
 */

interface MovieListContract {
    interface Presenter: IPresenter<View> {
        fun requestListMovies(category: CloudDataStore.Movies, page: Int = 1)
        fun restoreMovieList(movieList: List<MovieBriefModel>)
        fun getMovieList(): ArrayList<MovieBriefModel>
    }

    interface View: IView, IFragmentView {
        fun showMovieBriefList(movieList: List<MovieBriefModel>)
    }
}