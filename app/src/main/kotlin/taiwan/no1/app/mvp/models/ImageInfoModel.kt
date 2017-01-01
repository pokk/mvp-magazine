package taiwan.no1.app.mvp.models

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/1/17
 */

data class ImageInfoModel(val aspect_ratio: Double = 0.toDouble(),
                          val file_path: String? = null,
                          val height: Int = 0,
                          val iso_639_1: Any? = null,
                          val vote_average: Double = 0.toDouble(),
                          val vote_count: Int = 0,
                          val width: Int = 0)
