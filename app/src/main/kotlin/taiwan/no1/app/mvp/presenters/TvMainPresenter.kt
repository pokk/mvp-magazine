package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.TvMainContract

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
class TvMainPresenter: BasePresenter<TvMainContract.View>(), TvMainContract.Presenter {
    //region View implementation
    override fun init(view: TvMainContract.View) {
        super.init(view)
    }
    //endregion
}
