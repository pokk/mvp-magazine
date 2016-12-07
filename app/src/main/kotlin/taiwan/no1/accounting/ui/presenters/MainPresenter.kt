package taiwan.no1.accounting.ui.presenters

import taiwan.no1.accounting.domain.BaseCase
import taiwan.no1.accounting.domain.CreateFakeCase
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.mvp.models.FakeModel
import taiwan.no1.accounting.mvp.presenters.MainIPresenter
import taiwan.no1.accounting.mvp.views.MainView
import taiwan.no1.accounting.utilies.AppLog
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@PerActivity
class MainPresenter @Inject constructor(val fakeCase: BaseCase<CreateFakeCase.Requests>): MainIPresenter {
    private lateinit var view: MainView

    //region View implementation
    override fun setView(view: MainView) {
        this.view = view
    }

    override fun init() {
        AppLog.w(fakeCase)
        fakeCase.execute(CreateFakeCase.Requests(FakeModel("Jieyi", 19, "H")), FakeSubscriber())
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
    //endregion

    private inner class FakeSubscriber: rx.Subscriber<String>() {
        override fun onCompleted() {
            AppLog.w("hello")
        }

        override fun onError(e: Throwable) {
            AppLog.e("WTF")
        }

        override fun onNext(t: String) {
            AppLog.w(t)
        }

    }
}
