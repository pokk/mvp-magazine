package taiwan.no1.accounting.ui.presenters

import taiwan.no1.accounting.domain.BaseCase
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.mvp.presenters.MainPresenter
import taiwan.no1.accounting.mvp.views.MainView
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@PerActivity
class MainPresenter @Inject constructor(val fakeCase: BaseCase): MainPresenter {
    private lateinit var view: MainView

    //region View implementation
    override fun setView(view: MainView) {
        this.view = view
    }

    override fun init() {
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
    //endregion
}
