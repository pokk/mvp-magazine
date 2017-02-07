package taiwan.no1.app.mvp.presenters

import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.CastLists
import taiwan.no1.app.mvp.contracts.ActressMainContract
import taiwan.no1.app.mvp.models.CastListResModel
import taiwan.no1.app.utilies.AppLog
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
class ActressMainPresenter(val castCase: CastLists):
        BasePresenter<ActressMainContract.View>(), ActressMainContract.Presenter {
    //region View implementation
    override fun init(view: ActressMainContract.View) {
        super.init(view)
    }
    //endregion

    override fun requestListCasts(page: Int) {
        val request = CastLists.Requests(page)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.castCase.execute(request, subscriber<CastListResModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            view.obtainCastBriefList(it.results ?: Collections.emptyList())
        })
    }
}
