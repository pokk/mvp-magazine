package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieDetailModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/29/16
 */

interface MovieDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestMovieDetail(movieId: Int)
        fun requestMovieCasts(movieId: Int)
    }

    interface View: IView, IFragmentView {
        fun obtainMovieDetail(movieDetailModel: MovieDetailModel)
        fun obtainMovieCasts(castList: List<MovieCastsModel.CastBean>)
        fun obtainMovieCrews(crewList: List<MovieCastsModel.CrewBean>)
    }
}