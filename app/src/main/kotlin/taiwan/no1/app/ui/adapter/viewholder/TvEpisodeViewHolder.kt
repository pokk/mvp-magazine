package taiwan.no1.app.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.TvEpisodeAdapterContract
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying tv brief introduction view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TvEpisodeViewHolder(val view: View): BaseViewHolder<TvEpisodesModel>(view), TvEpisodeAdapterContract.View {
    @Inject
    lateinit var presenter: TvEpisodeAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val ivThumbnail by bindView<ImageView>(R.id.iv_thumbnail)
    private val tvEpisode by bindView<TextView>(R.id.tv_episode)
    private val tvAirDate by bindView<TextView>(R.id.tv_air_date)
    private val tvEpisodeTitle by bindView<TextView>(R.id.tv_episode_title)
    //endregion

    //region BaseViewHolder
    override fun initView(model: TvEpisodesModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)
    }

    override fun inject() {
        this.component.inject(TvEpisodeViewHolder@ this)
    }

    override fun initPresenter(model: TvEpisodesModel) {
        this.presenter.init(TvEpisodeViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showEpisodeThumbnail(episodeThumbnailUri: String) {
        this.imageLoader.display(episodeThumbnailUri, this.ivThumbnail, isFitCenter = false)
    }

    override fun showEpisodeNumber(episodeNumber: String) {
        this.tvEpisode.text = episodeNumber
    }

    override fun showEpisodeAirDate(episodeAirDate: String) {
        this.tvAirDate.text = episodeAirDate
    }

    override fun showEpisodeTitle(episodeTitle: String) {
        this.tvEpisodeTitle.text = episodeTitle
    }
    //endregion
}
