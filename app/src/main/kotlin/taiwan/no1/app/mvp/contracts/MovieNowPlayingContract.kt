package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/5/17
 */

interface MovieNowPlayingContract {
    interface Presenter: IPresenter<View> {
        fun requestNowPlayingMovies()
    }

    interface View: IView, IFragmentView {
        fun obtainMovieBriefList(movieList: List<MovieBriefModel>)
    }
}