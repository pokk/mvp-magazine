package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import taiwan.no1.app.ui.fragments.MovieDetailFragment

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/29/16
 */

class PopularMovieAdapter(val context: Context, val movies: List<MovieBriefModel>):
        RecyclerView.Adapter<PopularMovieAdapter.MovieBriefViewHolder>() {

    override fun getItemCount(): Int = this.movies.size

    override fun onBindViewHolder(holder: MovieBriefViewHolder, position: Int) {
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + this.movies[position].poster_path).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.ivPoster)
        holder.tvTitle.text = this.movies[position].title
        holder.item.setOnClickListener {
            RxBus.get().post(RxbusTag.FRAGMENT_NAVIGATOR,
                    MovieDetailFragment.newInstance(this.movies[position].id.toString()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBriefViewHolder {
        return MovieBriefViewHolder(LayoutInflater.from(this.context).
                inflate(R.layout.item_brief_movie, parent, false))
    }

    class MovieBriefViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val item by bindView<LinearLayout>(R.id.item_movie_brief)
        val ivPoster by bindView<ImageView>(R.id.iv_movie_poster)
        val tvTitle by bindView<TextView>(R.id.tv_title)
    }
}