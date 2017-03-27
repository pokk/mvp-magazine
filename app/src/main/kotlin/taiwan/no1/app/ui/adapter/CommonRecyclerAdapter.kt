package taiwan.no1.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import taiwan.no1.app.App
import taiwan.no1.app.internal.di.components.AdapterComponent
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.viewholder.BaseViewHolder
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import taiwan.no1.app.ui.adapter.viewholder.viewtype.ViewTypeFactory
import javax.inject.Inject

/**
 * A common recycler view's adapter for vary view holders.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class CommonRecyclerAdapter(var models: List<IVisitable>, val fragmentTag: Int = -1):
        RecyclerView.Adapter<BaseViewHolder<IVisitable>>() {
    @Inject
    lateinit var typeFactory: IViewTypeFactory

    init {
        AdapterComponent.Initializer.init(App.appComponent()).inject(CommonRecyclerAdapter@ this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<IVisitable>, position: Int) {
        holder.initView(this.models[position], position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<IVisitable> {
        val itemView: View = View.inflate(parent.context, ViewTypeFactory.TypeResource.values()[viewType].id, null)

        return this.typeFactory.createViewHolder(viewType, itemView) as BaseViewHolder<IVisitable>
    }

    override fun getItemCount(): Int = this.models.size

    override fun getItemViewType(position: Int): Int = this.models[position].type(this.typeFactory)

    /**
     * Add new data list into the recycler view.
     *
     * @param newModels new data as belong [IVisitable].
     */
    fun addItem(newModels: List<IVisitable>) {
        // TODO: 1/10/17 Maybe memory leak?!
        this.models = newModels
        this.notifyDataSetChanged()
    }
}