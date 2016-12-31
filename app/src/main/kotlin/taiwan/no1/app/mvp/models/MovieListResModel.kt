package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/31/16
 */

data class MovieListResModel(var page: Int = 0,
                             var total_results: Int = 0,
                             var total_pages: Int = 0,
                             var movieBriefModel: List<MovieBriefModel>? = null)
