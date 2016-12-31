package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/31/16
 */

data class MovieImagesModel(val backdrops: List<BackdropsBean>? = null,
                            val posters: List<PostersBean>? = null) {
    data class BackdropsBean(val aspect_ratio: Double = 0.toDouble(),
                             val file_path: String? = null,
                             val height: Int = 0,
                             val iso_639_1: Any? = null,
                             val vote_average: Double = 0.toDouble(),
                             val vote_count: Int = 0,
                             val width: Int = 0)

    data class PostersBean(val aspect_ratio: Double = 0.toDouble(),
                           val file_path: String? = null,
                           val height: Int = 0,
                           val iso_639_1: String? = null,
                           val vote_average: Double = 0.toDouble(),
                           val vote_count: Int = 0,
                           val width: Int = 0)
}

