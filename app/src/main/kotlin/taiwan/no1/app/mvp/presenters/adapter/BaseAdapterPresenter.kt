package taiwan.no1.app.mvp.presenters.adapter

import android.support.annotation.CallSuper
import dagger.internal.Preconditions
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

open class BaseAdapterPresenter<VH: IViewHolder>: IAdapterPresenter<VH> {
    lateinit protected var viewHolder: VH

    @CallSuper
    override fun init(viewHolder: VH) {
        Preconditions.checkNotNull(viewHolder)

        this.viewHolder = viewHolder
    }
}