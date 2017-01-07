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
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.MovieListFragment

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieRelatedViewHolder(view: View): BaseViewHolder(view) {
    val item by bindView<CardView>(R.id.item_cast)
    val ivPoster by bindView<ImageView>(R.id.iv_cast)
    val tvCharacter by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        model as MovieBriefModel

        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + model.poster_path).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(this.ivPoster)
        this.tvCharacter.text = model.original_title
        this.tvName.text = model.title
        this.item.setOnClickListener {
            RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                    Pair(MovieListFragment.NAVIGATOR_ARG_FRAGMENT,
                            MovieDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
                    Pair(MovieListFragment.NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
        }
    }
}