package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.TvListContract

/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */
class TvListPresenter: BasePresenter<TvListContract.View>(), TvListContract.Presenter {
    //region View implementation
    override fun init(view: TvListContract.View) {
        super.init(view)
    }
    //endregion
}
