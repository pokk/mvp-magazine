package taiwan.no1.app.mvp.contracts.adapter

import android.graphics.Bitmap
import android.support.annotation.ColorInt
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface CastListAdapterContract {
    interface Presenter: IAdapterPresenter<View, CastBriefModel> {
        fun onItemClicked(tag: Int)
        fun onResourceFinished(bitmap: Bitmap)
        fun captureColor(bitmap: Bitmap)
    }

    interface View: IViewHolder {
        fun showProfile(uri: String)
        fun showTvName(name: String)
        fun showTvNameBgColor(@ColorInt color: Int)
        fun resetHeightRatio(ratio: Float)
    }
}