package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   2/5/17
 */

data class TvBriefModel(val poster_path: String? = null,
                        val popularity: Double = 0.toDouble(),
                        val id: Int = 0,
                        val backdrop_path: String? = null,
                        val vote_average: Double = 0.toDouble(),
                        val overview: String? = null,
                        val first_air_date: String? = null,
                        val original_language: String? = null,
                        val vote_count: Int = 0,
                        val name: String? = null,
                        val original_name: String? = null,
                        val origin_country: List<String>? = null,
                        val genre_ids: List<Int>? = null): IVisitable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
}
