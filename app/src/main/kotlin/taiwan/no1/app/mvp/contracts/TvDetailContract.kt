package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   2/12/17
 */

interface TvDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestListTvs(id: Int = -1)
    }

    interface View: IView, IFragmentView
}