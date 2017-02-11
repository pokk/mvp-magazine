package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.CastListResModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/12/17
 */

interface ActressMainContract {
    interface Presenter: IPresenter<View> {
        fun requestListCasts(page: Int = 1)
    }

    interface View: IView, IFragmentView {
        fun showCastBriefList(castList: List<CastListResModel.CastBriefBean>)
    }
}