package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import butterknife.bindView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.google.android.youtube.player.YouTubeThumbnailView.OnInitializedListener
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.ACTIVITY_NAVIGATOR
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.ui.activities.VideoActivity
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.AppLog

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieTrailerViewHolder(val view: View): BaseViewHolder(view) {
    private val item by bindView<CardView>(R.id.item_trailer)
    private val yttnvTrailer by bindView<YouTubeThumbnailView>(R.id.yttnv_trailer)
    private lateinit var thumbnailLoader: YouTubeThumbnailLoader

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        (model as FilmVideoModel).let {
            this.yttnvTrailer.initialize(this.mContext.getString(R.string.youtube_api_key),
                    object: OnInitializedListener {
                        override fun onInitializationSuccess(thumbnailView: YouTubeThumbnailView,
                                                             loader: YouTubeThumbnailLoader) {
                            it.key?.let {
                                loader.apply {
                                    // TODO: 2017/02/15 ThumbnailLoader must release after this view is destroyed.
                                    thumbnailLoader = this
                                    this.setVideo(it)
                                }

                                item.setOnClickListener { v ->
                                    RxBus.get().post(ACTIVITY_NAVIGATOR, VideoActivity.newInstance(view.context, it))
                                }
                            } ?: AppLog.w("YouTube key is null...")
                        }

                        override fun onInitializationFailure(thumbnailView: YouTubeThumbnailView,
                                                             errorResult: YouTubeInitializationResult) {
                        }
                    })
        }
    }
}