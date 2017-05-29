package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import android.support.annotation.CallSuper
import android.support.v7.graphics.Palette
import dagger.internal.Preconditions
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

open class BaseAdapterPresenter<VH: IViewHolder, M: IVisitable>: IAdapterPresenter<VH, M> {
    lateinit protected var viewHolder: VH
    lateinit protected var model: M

    @CallSuper
    override fun init(viewHolder: VH, model: M) {
        Preconditions.checkNotNull(viewHolder)

        this.viewHolder = viewHolder
        this.model = model
    }

    fun captureColor(bitmap: Bitmap, colors: Int = 32, block: (palette: Palette) -> Unit) =
            Palette.from(bitmap).maximumColorCount(colors).generate().let { block(it) }
}