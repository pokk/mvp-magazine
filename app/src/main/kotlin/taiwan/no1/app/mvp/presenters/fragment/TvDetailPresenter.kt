package taiwan.no1.app.mvp.presenters.fragment

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.TVDetail
import taiwan.no1.app.mvp.contracts.fragment.TvDetailContract
import taiwan.no1.app.mvp.models.tv.TvDetailModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   2/12/17
 */
class TvDetailPresenter constructor(val tvDetail: TVDetail):
        BasePresenter<TvDetailContract.View>(), TvDetailContract.Presenter {

    //region Presenter implementation
    override fun init(view: TvDetailContract.View) {
        super.init(view)
    }

    override fun requestListTvs(id: Int) {
        val request = TVDetail.Requests(id)
        request.fragmentLifecycle = this.view.getLifecycle()
        // If declaring [subscriber] as a variable, it won't be used again.
        this.tvDetail.execute(request, subscriber<TvDetailModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            AppLog.w(it)
        })
    }
    //endregion
}
