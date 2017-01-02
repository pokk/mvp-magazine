package taiwan.no1.app.mvp.models

/**
 * A movie brief information.
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/28/16
 */

data class MovieBriefModel(val poster_path: String? = null,
                           val isAdult: Boolean = false,
                           val overview: String? = null,
                           val release_date: String? = null,
                           val id: Int = 0,
                           val original_title: String? = null,
                           val original_language: String? = null,
                           val title: String? = null,
                           val backdrop_path: String? = null,
                           val popularity: Double = 0.toDouble(),
                           val vote_count: Int = 0,
                           val isVideo: Boolean = false,
                           val vote_average: Double = 0.toDouble(),
                           val genre_ids: List<Int>? = null)
