package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.mvp.contracts.MainContract

/**
 *
 * @author  Jieyi
 * @since   12/20/16
 */

@PerActivity
class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {
    //region Presenter implementation
    override fun init(view: MainContract.View) {
        super.init(view)
    }
    //endregion
}