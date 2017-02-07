package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi
 * @since   2/5/17
 */

data class TvListResModel(val page: Int = 0,
                          val total_results: Int = 0,
                          val total_pages: Int = 0,
                          val results: List<TvBriefModel>? = null)
