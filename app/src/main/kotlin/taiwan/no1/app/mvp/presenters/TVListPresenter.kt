package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.TVListContract

/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */
class TVListPresenter: BasePresenter<TVListContract.View>(), TVListContract.Presenter {
    //region View implementation
    override fun init(view: TVListContract.View) {
        super.init(view)
    }
    //endregion
}
