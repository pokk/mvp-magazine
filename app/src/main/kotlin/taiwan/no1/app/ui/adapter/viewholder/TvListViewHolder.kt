package taiwan.no1.app.ui.adapter.viewholder

import android.graphics.Bitmap
import android.support.annotation.ColorInt
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotterknife.bindView
import me.gujun.android.taggroup.TagGroup
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying tv brief introduction view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class TvListViewHolder(val view: View) : BaseViewHolder<TvBriefModel>(view), TvListAdapterContract.View {
    @Inject
    lateinit var presenter: TvListAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val item by bindView<RelativeLayout>(R.id.item_tv_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_tv_poster)
    private val ivBackdrop by bindView<ImageView>(R.id.iv_tv_backdrop)
    private val ivBackdropFog by bindView<ImageView>(R.id.iv_fog)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val tvRelease by bindView<TextView>(R.id.tv_release_date)
    private val tvGenres by bindView<TagGroup>(R.id.tg_genres)
    //endregion

    //region BaseViewHolder
    override fun initView(model: TvBriefModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }
    }

    override fun inject() {
        this.component.inject(TvListViewHolder@ this)
    }

    override fun initPresenter(model: TvBriefModel) {
        this.presenter.init(TvListViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showTvPoster(uri: String) {
        this.imageLoader.display(uri, this.ivPoster, isFitCenter = false)
    }

    override fun showTvBackDrop(uri: String) {
        this.imageLoader.display(uri, listener = object : BitmapImageViewTarget(this.ivBackdrop) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                super.onResourceReady(resource, glideAnimation)
                this@TvListViewHolder.presenter.onBackdropResourceFinished(resource)
            }
        }, isFitCenter = false)
    }

    override fun showTvGenres(genres: List<String>) {
        this.tvGenres.setTags(genres)
    }

    override fun showTvTitle(title: String) {
        this.tvTitle.text = title
    }

    override fun showTvReleaseDate(date: String) {
        this.tvRelease.text = date
    }

    override fun showTvBackdropFrog(@ColorInt color: Int) {
        this.ivBackdropFog.setBackgroundColor(color)
    }
    //endregion
}
