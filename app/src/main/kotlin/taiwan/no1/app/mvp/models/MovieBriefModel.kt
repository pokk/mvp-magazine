package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory

/**
 * A movie brief information.
 *
 * @author  Jieyi
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
                           val genre_ids: List<Int>? = null): IVisitable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(MovieBriefModel@ this)
}
