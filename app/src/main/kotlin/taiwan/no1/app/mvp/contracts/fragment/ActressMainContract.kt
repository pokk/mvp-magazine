package taiwan.no1.app.mvp.contracts.fragment

import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView
import java.util.*

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/12/17
 */

interface ActressMainContract {
    interface Presenter: IPresenter<View> {
        fun requestListCasts(page: Int = 1)
        fun restoreCastList(castList: List<CastBriefModel>)
        fun getCastList(): ArrayList<CastBriefModel>
    }

    interface View: IView, IFragmentView {
        fun showCastBriefList(castList: List<CastBriefModel>)
    }
}