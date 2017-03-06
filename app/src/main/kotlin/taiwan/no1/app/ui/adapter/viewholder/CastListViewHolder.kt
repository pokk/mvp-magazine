package taiwan.no1.app.ui.adapter.viewholder

import android.graphics.Bitmap
import android.support.annotation.ColorInt
import android.support.v7.widget.CardView
import android.view.View
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.CastListAdapterContract
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.customize.AdjustHeightImageView
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying the cast view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class CastListViewHolder(val view: View): BaseViewHolder<CastBriefModel>(view), CastListAdapterContract.View {
    @Inject
    lateinit var presenter: CastListAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val item by bindView<CardView>(R.id.item_cast_brief)
    private val ivPoster by bindView<AdjustHeightImageView>(R.id.iv_cast_poster)
    private val tvName by bindView<TextView>(R.id.tv_name)
    //endregion

    //region BaseViewHolder
    override fun initView(model: CastBriefModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }
    }

    override fun inject() {
        this.component.inject(CastListViewHolder@ this)
    }

    override fun initPresenter(model: CastBriefModel) {
        this.presenter.init(CastListViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showProfile(uri: String) {
        this.imageLoader.display(uri, listener = object: BitmapImageViewTarget(this.ivPoster) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@CastListViewHolder.presenter.onResourceFinished(resource)
                super.onResourceReady(resource, glideAnimation)
            }
        })
    }

    override fun showTvName(name: String) {
        this.tvName.text = name
    }

    override fun showTvNameBgColor(@ColorInt color: Int) {
        this.tvName.setBackgroundColor(color)
    }

    override fun resetHeightRatio(ratio: Float) {
        this.ivPoster.heightRatio = ratio
    }
    //endregion
}