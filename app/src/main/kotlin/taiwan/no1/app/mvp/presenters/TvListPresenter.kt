package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.domain.usecase.TvLists
import taiwan.no1.app.mvp.contracts.TvListContract
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.utilies.AppLog

/**
 * @author  Jieyi
 * @since   1/7/17
 */
class TvListPresenter constructor(val tvCase: TvLists): BasePresenter<TvListContract.View>(), TvListContract.Presenter {
    //region Presenter implementation
    override fun init(view: TvListContract.View) {
        super.init(view)
    }

    override fun requestListTvs(category: CloudDataStore.Tvs, page: Int) {
        val request = TvLists.Requests(category, page)
        request.fragmentLifecycle = this.view.getLifecycle()
        // If declaring [subscriber] as a variable, it won't be used again.
        this.tvCase.execute(request, subscriber<List<TvBriefModel>>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            view.showTvBriefList(it)
        })
    }
    //endregion
}
