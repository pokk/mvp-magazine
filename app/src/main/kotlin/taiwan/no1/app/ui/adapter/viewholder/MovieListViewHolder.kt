package taiwan.no1.app.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ViewUtils

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieListViewHolder(val view: View): BaseViewHolder(view) {
    //    private val item by bindView<RelativeLayout>(R.id.item_movie_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_movie_poster)
    private val tvRelease by bindView<TextView>(R.id.tv_release)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val tvVote by bindView<TextView>(R.id.tv_vote)
    private val tvContent by bindView<TextView>(R.id.tv_brief_content)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        // Cast the model data type to MovieBriefModel.
        (model as MovieBriefModel).let {
            // FIXME: 2/8/17 Fit the cardview when it finished loading.
            ViewUtils.loadImageToView(this.mContext.applicationContext,
                    MovieDBConfig.BASE_IMAGE_URL + it.poster_path,
                    this.ivPoster, isFitCenter = false)
            this.tvRelease.text = it.release_date
            this.tvTitle.text = it.title
            this.tvVote.text = "${it.vote_average} / 10"
            this.tvContent.text = it.overview
//            this.item.setOnClickListener {
//                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
//                        Pair(NAVIGATOR_ARG_FRAGMENT,
//                                MovieDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
//                        Pair(NAVIGATOR_ARG_TAG, adapter.fragmentTag),
//                        Pair(NAVIGATOR_ARG_SHARED_ELEMENTS,
//                                hashMapOf(Pair(tvRelease, tvRelease.transitionName)))))
//            }
        }
    }
}