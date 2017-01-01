package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

data class MovieDetailModel(val isAdult: Boolean = false,
                            val backdrop_path: String? = null,
                            val belongs_to_collection: BelongsToCollectionBean? = null,
                            val budget: Int = 0,
                            val homepage: String? = null,
                            val id: Int = 0,
                            val imdb_id: String? = null,
                            val original_language: String? = null,
                            val original_title: String? = null,
                            val overview: String? = null,
                            val popularity: Double = 0.toDouble(),
                            val poster_path: String? = null,
                            val release_date: String? = null,
                            val revenue: Int = 0,
                            val runtime: Int = 0,
                            val status: String? = null,
                            val tagline: String? = null,
                            val title: String? = null,
                            val isVideo: Boolean = false,
                            val vote_average: Double = 0.toDouble(),
                            val vote_count: Int = 0,
                            val videos: VideosBean? = null,
                            val images: MovieImagesModel? = null,
                            val similar: MovieListResModel? = null,
                            val casts: MovieCastsModel? = null,
                            val genres: List<GenresBean>? = null,
                            val production_companies: List<ProductionCompaniesBean>? = null,
                            val production_countries: List<ProductionCountriesBean>? = null,
                            val spoken_languages: List<SpokenLanguagesBean>? = null) {

    data class BelongsToCollectionBean(val id: Int = 0,
                                       val name: String? = null,
                                       val poster_path: String? = null,
                                       val backdrop_path: String? = null)

    data class GenresBean(val id: Int = 0,
                          val name: String? = null)

    data class ProductionCompaniesBean(val name: String? = null,
                                       val id: Int = 0)

    data class ProductionCountriesBean(val iso_3166_1: String? = null,
                                       val name: String? = null)

    data class SpokenLanguagesBean(val iso_639_1: String? = null,
                                   val name: String? = null)

    data class VideosBean(val results: List<MovieVideosModel>? = null)
}