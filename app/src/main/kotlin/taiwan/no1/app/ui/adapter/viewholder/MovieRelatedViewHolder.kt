package taiwan.no1.app.ui.adapter.viewholder

import android.graphics.Bitmap
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.MovieRelatedAdapterContract
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying related movie of a movie view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieRelatedViewHolder(view: View): BaseViewHolder<IVisitable>(view), MovieRelatedAdapterContract.View {
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
    override fun initView(model: IVisitable, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }

        this.ivPoster.viewTreeObserver.addOnGlobalLayoutListener {
            this.ivPoster.measuredWidth.let {
                this.tvRelease.width = it
                this.tvName.width = it
            }
        }
    }

    override fun inject() {
        this.component.inject(MovieRelatedViewHolder@ this)
    }

    override fun initPresenter(model: IVisitable) {
        this.presenter.init(MovieRelatedViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showMoviePoster(uri: String) {
        this.imageLoader.display(uri, listener = object: BitmapImageViewTarget(this.ivPoster) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@MovieRelatedViewHolder.presenter.onResourceFinished(resource)
                super.onResourceReady(resource, glideAnimation)
            }
        })
    }

    override fun showMovieReleaseDate(date: String) {
        this.tvRelease.text = date
    }

    override fun showMovieTitle(title: String) {
        this.tvName.text = title
    }

    override fun setMovieTitleBg(color: Int) {
        this.tvRelease.setBackgroundColor(color)
        this.tvName.setBackgroundColor(color)
    }

    //endregion
}