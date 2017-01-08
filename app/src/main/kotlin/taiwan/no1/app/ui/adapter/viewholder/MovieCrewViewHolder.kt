package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCrewViewHolder(view: View): BaseViewHolder(view) {
    val item by bindView<CardView>(R.id.item_cast)
    val ivCast by bindView<ImageView>(R.id.iv_cast)
    val tvCharacter by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        (model as MovieCastsModel.CrewBean).let {
            Glide.with(this.context.applicationContext).
                    load(MovieDBConfig.BASE_IMAGE_URL + it.profile_path).
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).
                    into(this.ivCast)
            this.tvCharacter.text = it.job
            this.tvName.text = it.name
        }
    }
}