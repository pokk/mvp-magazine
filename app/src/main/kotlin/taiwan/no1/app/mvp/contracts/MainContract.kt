package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IActivityView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi

 * @since   12/8/16
 */

interface MainContract {
    interface Presenter: IPresenter<View>

    interface View: IView, IActivityView
}