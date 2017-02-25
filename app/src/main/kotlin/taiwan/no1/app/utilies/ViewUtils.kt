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

    fun resizeImageAsRatio(aspectRatio: Double, image: Bitmap): Bitmap = image.apply {
        val ratio: Double = this.width.toDouble() / this.height.toDouble()

        if (ratio > aspectRatio)
            this.width = (aspectRatio * this.height).toInt()
        else
            this.height = (this.width / aspectRatio).toInt()
    }
}