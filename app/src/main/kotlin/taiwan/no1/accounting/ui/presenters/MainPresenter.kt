package taiwan.no1.accounting.ui.presenters

import taiwan.no1.accounting.domain.CreateFakeUseCase
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.mvp.models.FakeModel
import taiwan.no1.accounting.mvp.presenters.MainIPresenter
import taiwan.no1.accounting.mvp.views.MainIView
import taiwan.no1.accounting.utilies.AppLog
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@PerActivity
class MainPresenter @Inject constructor(val fakeCase: CreateFakeUseCase): MainIPresenter {
    private lateinit var view: MainIView

    //region View implementation
    override fun setView(view: MainIView) {
        this.view = view
    }

    override fun init() {
        AppLog.w(fakeCase)
        fakeCase.execute(CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H")), FakeSubscriber())
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
    //endregion

    private inner class FakeSubscriber: rx.Subscriber<FakeModel>() {
        override fun onCompleted() {
        }

        override fun onError(e: Throwable) {
        }

        override fun onNext(t: FakeModel) {
        }
    }
}
