package taiwan.no1.app.mvp.contracts.adapter

import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface MovieCastAdapterContract {
    interface Presenter: IAdapterPresenter<View, FilmCastsModel.CastBean> {
        fun onItemClicked(tag: Int)
    }

    interface View: IViewHolder {
        fun showCastProfilePhoto(uri: String)
        fun showCastInfo(character: String, name: String)
    }
}