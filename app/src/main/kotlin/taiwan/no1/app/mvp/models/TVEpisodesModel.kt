package taiwan.no1.app.mvp.models

import taiwan.no1.app.data.entities.TVEpisodesEntity


/**
 * Created by weian on 2017/1/21.
 */
data class TVEpisodesModel(val air_date: String? = null,
                           val episode_number: Int = 0,
                           val name: String? = null,
                           val overview: String? = null,
                           val id: Int = 0,
                           val production_code: String? = null,
                           val season_number: Int = 0,
                           val still_path: String? = null,
                           val vote_count: Double = 0.toDouble(),
                           val videos: MovieDetailModel.VideosBean? = null) {
    class ImageBean(val stills: List<TVEpisodesEntity.ImagesBean.StillsBean>? = null) {
        class StillsBean(val aspect_ratio: Double = 0.toDouble(),
                         val file_path: String? = null,
                         val height: Int = 0,
                         val iso_639_1: Object? = null,
                         val vote_average: Double = 0.toDouble(),
                         val vote_count: Int = 0,
                         val width: Int = 0)
    }

    class GuestStarsBean(val id: Int = 0,
                         val name: String? = null,
                         val credit_id: String? = null,
                         val character: String? = null,
                         val order: Int = 0,
                         val profile_path: String? = null)
}