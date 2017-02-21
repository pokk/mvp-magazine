package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import butterknife.bindView
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener
import com.google.android.youtube.player.YouTubeThumbnailView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.TrailerAdapterContract
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.ui.activities.VideoActivity
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.listeners.YouTubeThumbnailInitListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieTrailerViewHolder(val view: View): BaseViewHolder<FilmVideoModel>(view), TrailerAdapterContract.View {
    @Inject
    lateinit var presenter: TrailerAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    private val item by bindView<CardView>(R.id.item_trailer)
    private val yttnvTrailer by bindView<YouTubeThumbnailView>(R.id.yttnv_trailer)
    private lateinit var containerListener: youTubeLoaderContainerListener

    interface youTubeLoaderContainerListener {
        fun keepLoader(loader: YouTubeThumbnailLoader)
    }

    override fun initView(model: FilmVideoModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        // FIXED: 2/21/17 Keep each of YouTubeThumbnailLoaders into the adapter. When the fragment which keep this
        // FIXED: ViewHolder is destroyed by lifecycle, let it release loaders thru the adapter's release method.
        this.yttnvTrailer.initialize(this.mContext.getString(R.string.youtube_api_key),
                object: YouTubeThumbnailInitListener(model.key.orEmpty()) {
                    override fun onInitializationSuccess(thumbnailView: YouTubeThumbnailView,
                                                         loader: YouTubeThumbnailLoader) {
                        if (this.youTubeKey.isNotEmpty()) {
                            loader.apply {
                                this@MovieTrailerViewHolder.containerListener.keepLoader(this)
                                this.setVideo(youTubeKey)
                                this.setOnThumbnailLoadedListener(this@MovieTrailerViewHolder.thumbnailLoadedListener)
                            }
                        }
                    }
                })
    }

    override fun inject() {
        this.component.inject(MovieTrailerViewHolder@ this)
    }

    override fun initPresenter() {
        this.presenter.init(MovieTrailerViewHolder@ this)
    }

    fun setYouTubeLoaderContainerListener(listener: (loader: YouTubeThumbnailLoader) -> Unit) {
        this.containerListener = object: youTubeLoaderContainerListener {
            override fun keepLoader(loader: YouTubeThumbnailLoader) {
                listener(loader)
            }
        }
    }

    private val thumbnailLoadedListener: OnThumbnailLoadedListener = object: OnThumbnailLoadedListener {
        override fun onThumbnailLoaded(thumbnailView: YouTubeThumbnailView, videoId: String) {
            item.apply {
                this.visibility = View.VISIBLE
                this.setOnClickListener {
                    RxBus.get().post(RxbusTag.ACTIVITY_NAVIGATOR, VideoActivity.newInstance(view.context, videoId))
                }
            }
        }

        override fun onThumbnailError(thumbnailView: YouTubeThumbnailView, result: ErrorReason) {
        }
    }
}