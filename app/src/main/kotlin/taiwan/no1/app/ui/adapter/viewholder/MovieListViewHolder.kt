package taiwan.no1.app.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.MovieListFragment

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieListViewHolder(view: View): BaseViewHolder(view) {
    private val item by bindView<LinearLayout>(R.id.item_movie_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_movie_poster)
    private val tvTitle by bindView<TextView>(R.id.tv_title)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        // Cast the model data type to MovieBriefModel.
        (model as MovieBriefModel).let {
            Glide.with(this.context.applicationContext).
                    load(MovieDBConfig.BASE_IMAGE_URL + it.poster_path).
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).
                    into(this.ivPoster)
            this.tvTitle.text = (it.release_date + "\n" + it.title)
            this.item.setOnClickListener {
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(MovieListFragment.NAVIGATOR_ARG_FRAGMENT,
                                MovieDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
                        Pair(MovieListFragment.NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
            }
        }
    }
}