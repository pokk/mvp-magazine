package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface IAdapterPresenter<in VH: IViewHolder, in M: IVisitable> {
    /**
     * Initial method.
     *
     * @param viewHolder view [IViewHolder].
     * @param model data model [IVisitable].
     */
    fun init(viewHolder: VH, model: M)
}