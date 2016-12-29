package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

data class MovieDetailModel(
        val isAdult: Boolean = false,
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
        val genres: List<GenresBean>? = null,
        val production_companies: List<ProductionCompaniesBean>? = null,
        val production_countries: List<ProductionCountriesBean>? = null,
        val spoken_languages: List<SpokenLanguagesBean>? = null)

class BelongsToCollectionBean {
    var id: Int = 0
    var name: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
}

class GenresBean {
    var id: Int = 0
    var name: String? = null
}

class ProductionCompaniesBean {
    var name: String? = null
    var id: Int = 0
}

class ProductionCountriesBean {
    var iso_3166_1: String? = null
    var name: String? = null
}

class SpokenLanguagesBean {
    var iso_639_1: String? = null
    var name: String? = null
}