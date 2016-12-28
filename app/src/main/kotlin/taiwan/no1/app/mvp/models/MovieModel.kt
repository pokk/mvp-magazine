package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/28/16
 */
data class MovieModel(val poster_path: String,
                      val isAdult: Boolean,
                      val overview: String,
                      val release_date: String,
                      val id: Int,
                      val original_title: String,
                      val original_language: String,
                      val title: String,
                      val backdrop_path: String,
                      val popularity: Double,
                      val vote_count: Int,
                      val isVideo: Boolean,
                      val vote_average: Double,
                      val genre_ids: List<Int>)
