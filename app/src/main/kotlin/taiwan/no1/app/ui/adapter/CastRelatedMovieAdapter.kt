package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.CreditsModel

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/30/16
 */

class CastRelatedMovieAdapter(val context: Context, val movies: List<CreditsModel.CastBean>):
        RecyclerView.Adapter<CastRelatedMovieAdapter.CastRelatedMovieViewHolder>() {
    override fun onBindViewHolder(holder: CastRelatedMovieAdapter.CastRelatedMovieViewHolder, position: Int) {
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + this.movies[position].poster_path).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.ivPoster)
        holder.tvCharacter.text = this.movies[position].release_date
        holder.tvName.text = this.movies[position].title
//        holder.item.setOnClickListener {
//            RxBus.get().post(RxbusTag.FRAGMENT_NAVIGATOR,
//                    MovieDetailFragment.newInstance(this.videoLists[position].id.toString()))
//        }
    }

    override fun getItemCount(): Int = this.movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastRelatedMovieViewHolder {
        return CastRelatedMovieViewHolder(LayoutInflater.from(this.context).
                inflate(R.layout.item_movie_casts_crews, parent, false))
    }

    class CastRelatedMovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val item by bindView<CardView>(R.id.item_cast)
        val ivPoster by bindView<ImageView>(R.id.iv_cast)
        val tvCharacter by bindView<TextView>(R.id.tv_character)
        val tvName by bindView<TextView>(R.id.tv_name)
    }
}