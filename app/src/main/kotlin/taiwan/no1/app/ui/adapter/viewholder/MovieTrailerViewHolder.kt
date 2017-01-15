package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.ACTIVITY_NAVIGATOR
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.activities.VideoActivity
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.AppLog
import taiwan.no1.app.utilies.ViewUtils
import taiwan.no1.app.utilies.YoutubeExtract

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieTrailerViewHolder(val view: View): BaseViewHolder(view) {
    private val item by bindView<CardView>(R.id.item_trailer)
    private val ivTrailer by bindView<ImageView>(R.id.iv_trailer)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        (model as MovieVideosModel).let {
            YoutubeExtract(it.key).startExtracting(object: YoutubeExtract.YouTubeExtractorListener {
                override fun onSuccess(result: YoutubeExtract.YouTubeExtractorResult) {
                    item.setOnClickListener {
                        RxBus.get().post(ACTIVITY_NAVIGATOR,
                                VideoActivity.newInstance(view.context, result.videoUri.toString()))
                    }

                    ViewUtils.loadImageToView(view.context.applicationContext,
                            result.defaultThumbUri.toString(),
                            ivTrailer)
                }

                override fun onFailure(error: Error) {
                    // FIXME: 1/15/17 Some url cannot be extract.
                    AppLog.e(error)
                }
            })

        }
    }
}