package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.mvp.contracts.MainContract

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

@PerActivity
class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {
    //region View implementation
    override fun init(view: MainContract.View) {
        super.init(view)
    }
    //endregion
}