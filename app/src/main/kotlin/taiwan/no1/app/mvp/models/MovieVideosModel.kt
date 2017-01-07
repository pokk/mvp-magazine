package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieVideosModel(val id: String? = null,
                            val iso_639_1: String? = null,
                            val iso_3166_1: String? = null,
                            val key: String? = null,
                            val name: String? = null,
                            val site: String? = null,
                            val size: Int = 0,
                            val type: String? = null): IVisitable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)
}