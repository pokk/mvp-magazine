package taiwan.no1.app.mvp.presenters.fragment

import com.devrapid.kotlinknifer.AppLog
import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.CastLists
import taiwan.no1.app.mvp.contracts.fragment.ActressMainContract
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.mvp.models.cast.CastListResModel
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
class ActressMainPresenter(val castCase: CastLists):
        BasePresenter<ActressMainContract.View>(), ActressMainContract.Presenter {
    private var castBriefModelList: List<CastBriefModel> = emptyList()

    //region Presenter implementation
    override fun init(view: ActressMainContract.View) {
        super.init(view)
    }

    override fun requestListCasts(page: Int) {
        this.view.showLoading()
        
        val request = CastLists.Requests(page)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.castCase.execute(request, subscriber<CastListResModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            it.results?.let {
                this.castBriefModelList += it
                view.showCastBriefList(this.castBriefModelList)
            }
        }.onCompleted { this.view.hideLoading() })
    }

    override fun restoreCastList(castList: List<CastBriefModel>) {
        this.castBriefModelList = castList.toList()
    }

    override fun getCastList(): ArrayList<CastBriefModel> = ArrayList(this.castBriefModelList)
    //endregion
}
