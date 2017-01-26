package taiwan.no1.app.ui.adapter.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter


/**
 * An abstract view holder.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    protected val mContext: Context = view.context

    /**
     * Set the views' properties.
     * NOTE: In Kotlin, can't use generic in constructor generic so you must cast the [model] to a type what
     * you want in the beginning.
     *
     * @param model a data model after input from a list.
     * @param position the index of a list.
     * @param adapter parent adapter.
     */
    abstract fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter)
}