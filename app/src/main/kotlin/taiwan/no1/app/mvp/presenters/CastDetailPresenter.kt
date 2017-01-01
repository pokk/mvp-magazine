package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.CastDetail
import taiwan.no1.app.mvp.contracts.CastDetailContract
import taiwan.no1.app.mvp.models.CastDetailModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/1/17
 */
class CastDetailPresenter constructor(val castDetailCase: CastDetail):
        BasePresenter<CastDetailContract.View>(), CastDetailContract.Presenter {
    //region Subscribers
    private val castDetailSub = subscriber<CastDetailModel>().onError {
        AppLog.e(it.message)
        AppLog.e(it)
    }.onNext {
        AppLog.w(it)
    }
    //endregion

    //region View implementation
    override fun init(view: CastDetailContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestCastDetail(castId: Int) {
        val request = CastDetail.Requests(castId)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.castDetailCase.execute(request, this.castDetailSub)
    }
}
