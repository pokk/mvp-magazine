package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   2017/01/04
 */

data class CreditsModel(val cast: List<CastBean>? = null,
                        val crew: List<CrewBean>? = null) {
    data class CastBean(val isAdult: Boolean = false,
                        val character: String? = null,
                        val credit_id: String? = null,
                        val id: Int = 0,
                        val original_title: String? = null,
                        val poster_path: String? = null,
                        val release_date: String? = null,
                        val title: String? = null,
                        val media_type: String? = null,
                        val episode_count: Int = 0,
                        val first_air_date: String? = null,
                        val name: String? = null,
                        val original_name: String? = null): IVisitable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
    }

    data class CrewBean(val isAdult: Boolean = false,
                        val credit_id: String? = null,
                        val department: String? = null,
                        val id: Int = 0,
                        val job: String? = null,
                        val original_title: String? = null,
                        val poster_path: Any? = null,
                        val release_date: String? = null,
                        val title: String? = null,
                        val media_type: String? = null): IVisitable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
    }
}