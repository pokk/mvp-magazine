package taiwan.no1.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeThumbnailLoader
import taiwan.no1.app.App
import taiwan.no1.app.internal.di.components.AdapterComponent
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.viewholder.BaseViewHolder
import taiwan.no1.app.ui.adapter.viewholder.MovieTrailerViewHolder
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
        RecyclerView.Adapter<BaseViewHolder>() {
    @Inject
    lateinit var typeFactory: IViewTypeFactory
    // Keeping the YouTube loaders from TrailerViewHolder.
    private val loaderContainer: MutableList<YouTubeThumbnailLoader> = mutableListOf()

    init {
        AdapterComponent.Initializer.init(App.appComponent()).inject(CommonRecyclerAdapter@ this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.initView(this.models[position], position, this)
        // YouTube thumbnail is used only by TrailerViewHolder.
        if (holder is MovieTrailerViewHolder)
            holder.setYouTubeLoaderContainerListener { this@CommonRecyclerAdapter.loaderContainer.add(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView: View = View.inflate(parent.context, ViewTypeFactory.TypeResource.values()[viewType].id, null)

        return this.typeFactory.createViewHolder(viewType, itemView)
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

    /**
     * For releasing all [YouTubeThumbnailLoader], they are created by .
     */
    fun releaseYouTubeLoader() {
        this.loaderContainer.forEach { it.release() }
    }
}