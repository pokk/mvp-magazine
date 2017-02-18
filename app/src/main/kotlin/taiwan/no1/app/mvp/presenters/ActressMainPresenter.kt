package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.CastLists
import taiwan.no1.app.mvp.contracts.ActressMainContract
import taiwan.no1.app.mvp.models.cast.CastListResModel
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
class ActressMainPresenter(val castCase: CastLists):
        BasePresenter<ActressMainContract.View>(), ActressMainContract.Presenter {
    //region Presenter implementation
    override fun init(view: ActressMainContract.View) {
        super.init(view)
    }

    override fun requestListCasts(page: Int) {
        val request = CastLists.Requests(page)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.castCase.execute(request, subscriber<CastListResModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            view.showCastBriefList(it.results.orEmpty())
        })
    }
    //endregion
}
