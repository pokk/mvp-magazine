package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/12
 */

interface MovieMainContract {
    interface Presenter: IPresenter<View>

    interface View: IView, IFragmentView
}