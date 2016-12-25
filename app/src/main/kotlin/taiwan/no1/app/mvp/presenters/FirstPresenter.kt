package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.CreateFakeUseCase
import taiwan.no1.app.mvp.contracts.FirstContract
import taiwan.no1.app.mvp.models.FakeModel

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class FirstPresenter constructor(val fakeCase: CreateFakeUseCase):
        BasePresenter<FirstContract.View>(), FirstContract.Presenter {
    //region Subscribers
    private val fakeSubscriber = subscriber<FakeModel>().onCompleted { }.onError { }.onNext { }
    //endregion

    //region View implementation
    override fun init(view: FirstContract.View) {
        super.init(view)

        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        request.fragmentLifecycle = this.view.getLifecycle()
        this.fakeCase.execute(request, this.fakeSubscriber)
    }
    //endregion
}
