package taiwan.no1.app.mvp.contracts.adapter

import android.graphics.Bitmap
import android.support.annotation.ColorInt
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface TvListAdapterContract {
    interface Presenter: IAdapterPresenter<View, TvBriefModel> {
        fun onItemClicked(tag: Int)
        fun onBackdropResourceFinished(bitmap: Bitmap)
        fun captureBbColor(bitmap: Bitmap)
    }

    interface View: IViewHolder {
        fun showTvPoster(uri: String)
        fun showTvBackDrop(uri: String)
        fun showTvGenres(genres: String)
        fun showTvTitle(title: String)
        fun showTvReleaseDate(date: String)
        fun showTvBackdropFrog(@ColorInt color: Int)
    }
}