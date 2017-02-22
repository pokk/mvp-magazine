package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.MovieCastRelatedAdapterContract
import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying a related cast of the cast view of the MVP architecture's V.
 * 
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCastRelatedViewHolder(view: View): BaseViewHolder<CreditsInFilmModel.CastInFilmBean>(view), MovieCastRelatedAdapterContract.View {
    @Inject
    lateinit var presenter: MovieCastRelatedAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    val item by bindView<CardView>(R.id.item_cast)
    val ivPoster by bindView<ImageView>(R.id.iv_cast)
    val tvReleaseDate by bindView<TextView>(R.id.tv_character)
    val tvMovieTitle by bindView<TextView>(R.id.tv_name)
    //endregion

    //region BaseViewHolder
    override fun initView(model: CreditsInFilmModel.CastInFilmBean, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }
    }

    override fun inject() {
        this.component.inject(MovieCastRelatedViewHolder@ this)
    }

    override fun initPresenter(model: CreditsInFilmModel.CastInFilmBean) {
        this.presenter.init(MovieCastRelatedViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showMoviePoster(uri: String) {
        this.imageLoader.display(uri, listener = GlideResizeTargetListener(this.ivPoster, this.item))
    }

    override fun showMovieReleaseDate(date: String) {
        this.tvReleaseDate.text = date
    }

    override fun showMovieTitle(title: String) {
        this.tvMovieTitle.text = title
    }
    //endregion
}