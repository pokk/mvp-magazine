package taiwan.no1.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.viewholder.BaseViewHolder
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import taiwan.no1.app.ui.adapter.viewholder.viewtype.ViewTypeFactory

/**
 * A common recycler view's adapter for vary view holders.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class CommonRecyclerAdapter(var models: List<IVisitable>, val fragmentTag: Int = -1):
        RecyclerView.Adapter<BaseViewHolder>() {
    private val typeFactory: IViewTypeFactory = ViewTypeFactory()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.initView(this.models[position], position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView: View = View.inflate(parent.context, ViewTypeFactory.TypeResource.values()[viewType].id, null)

        return this.typeFactory.createViewHolder(viewType, itemView)
    }

    override fun getItemCount(): Int = this.models.size

    override fun getItemViewType(position: Int): Int = this.models[position].type(this.typeFactory)

    fun addItem(newModels: List<IVisitable>) {
        // TODO: 1/10/17 Maybe memory leak?!
        this.models = newModels
        this.notifyDataSetChanged()
    }
}