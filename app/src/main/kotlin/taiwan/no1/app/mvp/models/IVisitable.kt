package taiwan.no1.app.mvp.models

import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory


/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */

interface IVisitable {
    fun type(typeFactory: IViewTypeFactory): Int
}