package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView
import java.util.*

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/7/17
 */

interface TvListContract {
    interface Presenter: IPresenter<View> {
        fun requestListTvs(category: CloudDataStore.Tvs, page: Int = 1)
        fun restoreTvList(tvList: List<TvBriefModel>)
        fun getTvList(): ArrayList<TvBriefModel>
    }

    interface View: IView, IFragmentView {
        fun showTvBriefList(tvList: List<TvBriefModel>)
    }
}