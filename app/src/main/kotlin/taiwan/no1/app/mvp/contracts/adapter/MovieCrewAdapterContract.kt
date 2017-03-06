package taiwan.no1.app.mvp.contracts.adapter

import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 * This specifies the contract between the [IAdapterPresenter] and the [IViewHolder].
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface MovieCrewAdapterContract {
    interface Presenter: IAdapterPresenter<View, FilmCastsModel.CrewBean>

    interface View: IViewHolder {
        fun showCrewProfilePhoto(uri: String)
        fun showCrewCharacter(character: String)
        fun showCrewName(name: String)
    }
}