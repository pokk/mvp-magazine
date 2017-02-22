package taiwan.no1.app.mvp.contracts.adapter

import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface TrailerAdapterContract {
    interface Presenter: IAdapterPresenter<View, FilmVideoModel>

    interface View: IViewHolder
}