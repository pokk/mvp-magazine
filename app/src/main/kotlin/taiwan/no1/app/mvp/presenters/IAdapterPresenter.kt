package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.views.IView
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

interface IAdapterPresenter<in VH: IViewHolder> {
    /**
     * Initial method.
     *
     * @param view [IView]
     */
    fun init(viewHolder: VH)
}