package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.ACTIVITY_YOUTUBE_VIEW
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ViewUtils

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieTrailerViewHolder(val view: View): BaseViewHolder(view) {
    private val item by bindView<CardView>(R.id.item_trailer)
    private val ivTrailer by bindView<ImageView>(R.id.iv_trailer)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        (model as MovieVideosModel).let {
            val key: String = it.key.orEmpty()
            val youtubeThumbnail: String = "http://img.youtube.com/vi/$key/0.jpg"

            item.setOnClickListener {
                RxBus.get().post(ACTIVITY_YOUTUBE_VIEW, key)
            }
            ViewUtils.loadImageToView(view.context.applicationContext,
                    youtubeThumbnail,
                    ivTrailer)
        }


    }
}