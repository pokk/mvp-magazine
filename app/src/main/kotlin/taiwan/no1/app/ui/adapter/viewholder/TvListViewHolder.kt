package taiwan.no1.app.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.TvBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ViewUtils

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class TvListViewHolder(val view: View): BaseViewHolder(view) {
    private val item by bindView<RelativeLayout>(R.id.item_tv_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_tv_poster)
    private val ivBackdrop by bindView<ImageView>(R.id.iv_tv_backdrop)
    private val tvTitle by bindView<TextView>(R.id.tv_title)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        // Cast the model data type to MovieBriefModel.
        (model as TvBriefModel).let {
            ViewUtils.loadImageToView(this.mContext.applicationContext,
                    MovieDBConfig.BASE_IMAGE_URL + it.poster_path,
                    this.ivPoster, isFitCenter = false)
            ViewUtils.loadImageToView(this.mContext.applicationContext,
                    MovieDBConfig.BASE_IMAGE_URL + it.backdrop_path,
                    this.ivBackdrop, isFitCenter = false)
            this.tvTitle.text = it.name
            this.item.setOnClickListener {
                // TODO: 2/5/17 Make the tv detail.
//                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
//                        Pair(NAVIGATOR_ARG_FRAGMENT,
//                                MovieDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
//                        Pair(NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
            }
        }
    }
}
