package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   12/8/16
 */

interface MovieListContract {
    interface Presenter: IPresenter<View> {
        fun requestListMovies(category: CloudDataStore.Movies, page: Int = 1)
    }

    interface View: IView, IFragmentView {
        fun showMovieBriefList(movieList: List<MovieBriefModel>)
    }
}