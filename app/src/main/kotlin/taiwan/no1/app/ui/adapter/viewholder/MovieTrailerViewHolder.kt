package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.VideoView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter

/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieTrailerViewHolder(view: View): BaseViewHolder(view) {
    private val item by bindView<CardView>(R.id.item_trailer)
    private val vvTrailer by bindView<VideoView>(R.id.vv_trailer)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        model as MovieVideosModel
    }
}