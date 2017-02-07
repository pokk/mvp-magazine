package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.mvp.models.TvBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/7/17
 */

interface TvListContract {
    interface Presenter: IPresenter<View> {
        fun requestListTvs(category: CloudDataStore.Tvs, page: Int = 1)
    }

    interface View: IView, IFragmentView {
        fun obtainTvBriefList(tvList: List<TvBriefModel>)
    }
}