package taiwan.no1.app.mvp.models

import taiwan.no1.app.data.entities.TVSeasonsEntity

/**
 * Created by weian on 2017/1/21.
 */

data class TVSeasonsModel(val _id: String? = null,
                          val air_date: String? = null,
                          val name: String? = null,
                          val overview: String? = null,
                          val id: Int = 0,
                          val poster_path: String? = null,
                          val season_number: Int = 0,
                          val episodes: List<TVSeasonsEntity.EpisodesBean>? = null) {
    class EpisodesBean(val )
}