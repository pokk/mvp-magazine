package taiwan.no1.app.mvp.contracts.fragment

import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   2/12/17
 */

interface TvDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestListTvs(id: Int = -1)
    }

    interface View: IView, IFragmentView {
        fun showTvBackdrop(uri: String)
        fun showTvBriefInfo(title: String, status: String, rate: String, lastAirDate: String)
    }
}