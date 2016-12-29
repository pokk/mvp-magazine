package taiwan.no1.app.mvp.models

/**
 * @author Jieyi
 * *
 * @version 0.0.1
 * *
 * @since 12/29/16
 */

data class MovieCastsModel(
        val id: Int = 0,
        val cast: List<taiwan.no1.app.data.entities.MovieCastsEntity.CastBean>? = null,
        val crew: List<taiwan.no1.app.data.entities.MovieCastsEntity.CrewBean>? = null) {
    data class CastBean(val cast_id: Int = 0,
                        val character: String? = null,
                        val credit_id: String? = null,
                        val id: Int = 0,
                        val name: String? = null,
                        val order: Int = 0,
                        val profile_path: String? = null)

    data class CrewBean(val credit_id: String? = null,
                        val department: String? = null,
                        val id: Int = 0,
                        val job: String? = null,
                        val name: String? = null,
                        val profile_path: String? = null)
}

