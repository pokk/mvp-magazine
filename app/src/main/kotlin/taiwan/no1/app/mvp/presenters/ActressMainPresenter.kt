package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.ActressMainContract

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
class ActressMainPresenter: BasePresenter<ActressMainContract.View>(), ActressMainContract.Presenter {
    //region View implementation
    override fun init(view: ActressMainContract.View) {
        super.init(view)
    }
    //endregion
}
