package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.MovieBriefModel

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/30/16
 */

class MovieRelatedAdapter(val context: Context, val movies: List<MovieBriefModel>):
        RecyclerView.Adapter<MovieRelatedAdapter.MovieRelatedViewHolder>() {
    override fun onBindViewHolder(holder: MovieRelatedAdapter.MovieRelatedViewHolder, position: Int) {
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + this.movies[position].poster_path).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.ivPoster)
        holder.tvCharacter.text = this.movies[position].original_title
        holder.tvName.text = this.movies[position].title
    }

    override fun getItemCount(): Int = this.movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRelatedViewHolder {
        return MovieRelatedViewHolder(LayoutInflater.from(this.context).
                inflate(R.layout.item_movie_casts_crews, parent, false))
    }

    class MovieRelatedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val item by bindView<RelativeLayout>(R.id.item_cast)
        val ivPoster by bindView<ImageView>(R.id.iv_cast)
        val tvCharacter by bindView<TextView>(R.id.tv_character)
        val tvName by bindView<TextView>(R.id.tv_name)
    }
}