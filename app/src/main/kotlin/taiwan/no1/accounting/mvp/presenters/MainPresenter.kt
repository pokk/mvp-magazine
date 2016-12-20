package taiwan.no1.accounting.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.accounting.domain.CreateFakeUseCase
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.mvp.contracts.MainContract
import taiwan.no1.accounting.mvp.models.FakeModel
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@PerActivity
class MainPresenter @Inject constructor(val fakeCase: CreateFakeUseCase): BasePresenter<MainContract.View>(),
        MainContract.Presenter {

    //region Subscribers
    private val fakeSubscriber = subscriber<FakeModel>().onCompleted { }.onError { }.onNext { }
    //endregion

    //region View implementation
    override fun init(view: MainContract.View) {
        super.init(view)
        
        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        request.fragmentLifecycle = this.view.getLifecycle()
        this.fakeCase.execute(request, this.fakeSubscriber)
    }
    //endregion
}
