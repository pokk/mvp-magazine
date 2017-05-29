package taiwan.no1.app.mvp.contracts.adapter

import android.graphics.Bitmap
import android.support.annotation.ColorInt
import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 * This specifies the contract between the [IAdapterPresenter] and the [IViewHolder].
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface MovieCastRelatedAdapterContract {
    interface Presenter: IAdapterPresenter<View, CreditsInFilmModel.CastInFilmBean> {
        fun onItemClicked(tag: Int)
        fun onResourceFinished(bitmap: Bitmap)

    }

    interface View: IViewHolder {
        fun showMoviePoster(uri: String)
        fun showMovieReleaseDate(date: String)
        fun showMovieTitle(title: String)
        fun setMovieTitleBg(@ColorInt color: Int)
    }
}