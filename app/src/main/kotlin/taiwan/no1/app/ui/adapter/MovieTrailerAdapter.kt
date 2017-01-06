package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.MovieVideosModel

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/6/17
 */

class MovieTrailerAdapter(val context: Context, val videoLists: List<MovieVideosModel>):
        RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder>() {

    override fun onBindViewHolder(holder: MovieTrailerViewHolder, position: Int) {
        // TODO: 1/6/17 Set the video preview and play the video from youtube uri.
    }

    override fun getItemCount(): Int = videoLists.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieTrailerViewHolder {
        return MovieTrailerViewHolder(LayoutInflater.from(this.context).
                inflate(R.layout.item_movie_trailers, parent, false))
    }

    class MovieTrailerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val item by bindView<CardView>(R.id.item_trailer)
        val vvTrailer by bindView<VideoView>(R.id.vv_trailer)
    }
}