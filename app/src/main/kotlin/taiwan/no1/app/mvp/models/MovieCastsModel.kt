package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory

/**
 * @author Jieyi
 * @since 12/29/16
 */

data class MovieCastsModel(val cast: List<CastBean>? = null,
                           val crew: List<CrewBean>? = null) {
    data class CastBean(val cast_id: Int = 0,
                        val character: String? = null,
                        val credit_id: String? = null,
                        val id: Int = 0,
                        val name: String? = null,
                        val order: Int = 0,
                        val profile_path: String? = null): IVisitable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
    }

    data class CrewBean(val credit_id: String? = null,
                        val department: String? = null,
                        val id: Int = 0,
                        val job: String? = null,
                        val name: String? = null,
                        val profile_path: String? = null): IVisitable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
    }
}

