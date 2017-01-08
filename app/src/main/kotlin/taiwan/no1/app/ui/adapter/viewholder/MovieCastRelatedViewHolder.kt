package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.CreditsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.MovieListFragment

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCastRelatedViewHolder(view: View): BaseViewHolder(view) {
    val item by bindView<CardView>(R.id.item_cast)
    val ivPoster by bindView<ImageView>(R.id.iv_cast)
    val tvReleaseDate by bindView<TextView>(R.id.tv_character)
    val tvMovieTitle by bindView<TextView>(R.id.tv_name)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        (model as CreditsModel.CastBean).let {
            Glide.with(this.context.applicationContext).
                    load(MovieDBConfig.BASE_IMAGE_URL + it.poster_path).
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).
                    into(this.ivPoster)
            this.tvReleaseDate.text = it.release_date
            this.tvMovieTitle.text = it.title
            this.item.setOnClickListener {
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(MovieListFragment.NAVIGATOR_ARG_FRAGMENT,
                                MovieDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
                        Pair(MovieListFragment.NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
            }
        }
    }
}