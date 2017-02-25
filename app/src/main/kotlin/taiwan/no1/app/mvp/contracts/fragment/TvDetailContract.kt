package taiwan.no1.app.mvp.contracts.fragment

import android.support.annotation.IntRange
import android.widget.ImageView
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   2/12/17
 */

interface TvDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestListTvs(id: Int = -1)
        fun onResourceFinished(view: android.view.View, tag: Int)
        fun scrollBackdropTo(index: Int)
    }

    interface View: IView, IFragmentView {
        fun showTvBackdrops(viewList: List<android.view.View>)
        fun showTvSingleBackdrop(uri: String, imageview: ImageView)
        fun setLeftSlideButton(@IntRange(from = 0, to = 8) visibility: Int)
        fun setRightSlideButton(@IntRange(from = 0, to = 8) visibility: Int)
        fun showTvBriefInfo(title: String, status: String, rate: String, startAirDate: String, lastAirDate: String)
        fun showTvDetail(overview: String, homepage: String, productions: String)
    }
}