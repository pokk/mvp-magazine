package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.CastDetailModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/1/17
 */

interface CastDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestCastDetail(castId: Int)
    }

    interface View: IView, IFragmentView {
        fun showCastDetail(castDetailModel: CastDetailModel)
//        fun showCastPosters()
//        fun showCastProPic()
//        fun showCastBase(job: String, name: String)
//        fun showCastDetail(bio: String, birthday: String, bron: String, homepage: String, deathday: String)
//        fun showRelatedMovie()
    }
}