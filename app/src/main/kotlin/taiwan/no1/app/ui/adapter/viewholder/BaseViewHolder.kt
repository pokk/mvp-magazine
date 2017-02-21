package taiwan.no1.app.ui.adapter.viewholder

import android.content.Context
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import taiwan.no1.app.App
import taiwan.no1.app.internal.di.components.AdapterComponent
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.views.IViewHolder
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter


/**
 * An abstract view holder.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

abstract class BaseViewHolder<in M: IVisitable>(view: View): RecyclerView.ViewHolder(view), IViewHolder {
    protected val mContext: Context = view.context
    protected val component: AdapterComponent = AdapterComponent.Initializer.init(App.appComponent())

    /**
     * Set the views' properties.
     * NOTE: In Kotlin, can't use generic in constructor generic so you must cast the [model] to a type what you want
     * in the beginning.
     *
     * @param model a data model after input from a list.
     * @param position the index of a list.
     * @param adapter parent adapter.
     */
    @CallSuper
    open fun initView(model: M, position: Int, adapter: CommonRecyclerAdapter) {
        this.inject()
        this.initPresenter()
    }

    /**
     * Injected the view holder's presenter.
     */
    abstract protected fun inject()

    abstract protected fun initPresenter()
}