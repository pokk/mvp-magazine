package taiwan.no1.app.utilies

import android.graphics.Bitmap
import android.view.View

/**
 * View utility.
 *
 * @author  Jieyi
 * @since   1/14/17
 */
object ViewUtils {
    fun resizeView(view: View, width: Int? = null, height: Int? = null) {
        view.layoutParams?.apply {
            height?.let {
                this.height = it
            }
            width?.let {
                this.width = it
            }
        }
    }

    fun resizeImageAsRatio(aspectRatio: Double, image: Bitmap): Bitmap = image.also {
        val ratio: Double = it.width.toDouble() / it.height.toDouble()

        if (ratio > aspectRatio)
            it.width = (aspectRatio * it.height).toInt()
        else
            it.height = (it.width / aspectRatio).toInt()
    }
}