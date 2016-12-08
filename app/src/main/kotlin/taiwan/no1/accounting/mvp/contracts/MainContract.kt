package taiwan.no1.accounting.mvp.contracts

import taiwan.no1.accounting.mvp.presenters.IPresenter
import taiwan.no1.accounting.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/8/16
 */

interface MainContract {
    interface Presenter: IPresenter<View>

    interface View: IView
}