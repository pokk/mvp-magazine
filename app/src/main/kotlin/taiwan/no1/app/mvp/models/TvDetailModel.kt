package taiwan.no1.app.mvp.models

import taiwan.no1.app.data.entities.MovieDetailEntity
import taiwan.no1.app.data.entities.MovieImagesEntity
import taiwan.no1.app.data.entities.MovieListResEntity

/**
 *
 * @author  Jieyi
 * @since   1/13/17
 */

data class TvDetailModel(val backdrop_path: String? = null,
                         val first_air_date: String? = null,
                         val homepage: String? = null,
                         val id: Int = 0,
                         val in_production: Boolean = false,
                         val last_air_date: String? = null,
                         val name: String? = null,
                         val number_of_episodes: Int = 0,
                         val number_of_seasons: Int = 0,
                         val original_language: String? = null,
                         val original_name: String? = null,
                         val overview: String? = null,
                         val popularity: Double = 0.toDouble(),
                         val poster_path: String? = null,
                         val status: String? = null,
                         val type: String? = null,
                         val vote_average: Double = 0.toDouble(),
                         val vote_count: Int = 0,
                         val videos: MovieDetailModel.VideosBean? = null,
                         val images: MovieImagesModel? = null,
                         val similar: MovieListResModel? = null,
                         val created_by: List<CreatedByBean>? = null,
                         val episode_run_time: List<Int>? = null,
                         val genres: List<GenresBean>? = null,
                         val languages: List<String>? = null,
                         val networks: List<NetworksBean>? = null,
                         val origin_country: List<String>? = null,
                         val production_companies: List<ProductionCompaniesBean>? = null,
                         val seasons: List<SeasonsBean>? = null) {

    class CreatedByBean(val id: Int = 0,
                        val name: String? = null,
                        val profile_path: String? = null)

    class GenresBean(val id: Int = 0,
                     val name: String? = null)

    class NetworksBean(val id: Int = 0,
                       val name: String? = null)

    class ProductionCompaniesBean(val name: String? = null,
                                  val id: Int = 0)

    class SeasonsBean(val air_date: String? = null,
                      val episode_count: Int = 0,
                      val id: Int = 0,
                      val poster_path: String? = null,
                      val season_number: Int = 0)
}