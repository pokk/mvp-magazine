package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.MovieRelatedAdapterContract
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieRelatedViewHolder(view: View): BaseViewHolder<MovieBriefModel>(view), MovieRelatedAdapterContract.View {
    @Inject
    lateinit var presenter: MovieRelatedAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    val item by bindView<CardView>(R.id.item_cast)
    val ivPoster by bindView<ImageView>(R.id.iv_cast)
    val tvRelease by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)
    //endregion

    //region BaseViewHolder
    override fun initView(model: MovieBriefModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }
    }

    override fun inject() {
        this.component.inject(MovieRelatedViewHolder@ this)
    }

    override fun initPresenter(model: MovieBriefModel) {
        this.presenter.init(MovieRelatedViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showMoviePoster(uri: String) {
        this.imageLoader.display(uri, listener = GlideResizeTargetListener(this.ivPoster, this.item))
    }

    override fun showMovieReleaseDate(date: String) {
        this.tvRelease.text = date
    }

    override fun showMovieTitle(title: String) {
        this.tvName.text = title
    }
    //endregion
}