package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.TvSeasonAdapterContract
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject


/**
 * A [BaseViewHolder] of displaying tv brief introduction view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TvSeasonViewHolder(val view: View): BaseViewHolder<TvSeasonsModel>(view), TvSeasonAdapterContract.View {
    @Inject
    lateinit var presenter: TvSeasonAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val item by bindView<CardView>(R.id.item_tv_season)
    private val ivPoster by bindView<ImageView>(R.id.iv_tv_poster)
    private val tvEpisodeCount by bindView<TextView>(R.id.tv_episode_count)
    private val tvSeasonNumber by bindView<TextView>(R.id.tv_season_number)
    private val tvAirDate by bindView<TextView>(R.id.tv_air_date)
    //endregion

    //region BaseViewHolder
    override fun initView(model: TvSeasonsModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }

        this.ivPoster.viewTreeObserver.addOnGlobalLayoutListener {
            this.ivPoster.measuredWidth.let {
                this.tvEpisodeCount.width = it
                this.tvSeasonNumber.width = it
                this.tvAirDate.width = it
            }
        }
    }

    override fun inject() {
        this.component.inject(TvSeasonViewHolder@ this)
    }

    override fun initPresenter(model: TvSeasonsModel) {
        this.presenter.init(TvSeasonViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showTvPoster(uri: String) {
        this.imageLoader.display(uri, this.ivPoster)
    }

    override fun showTvEpisodeNumber(episodeNumber: String) {
        this.tvEpisodeCount.text = episodeNumber
    }

    override fun showTvSeasonNumber(seasonNumber: String) {
        this.tvSeasonNumber.text = seasonNumber
    }

    override fun showTvAirDate(airDate: String) {
        this.tvAirDate.text = airDate
    }
    //endregion
}
