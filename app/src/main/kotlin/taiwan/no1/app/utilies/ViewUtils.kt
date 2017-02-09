package taiwan.no1.app.utilies

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * View utility.
 *
 * @author  Jieyi
 * @since   1/14/17
 */
object ViewUtils {
    fun resizeView(view: View, height: Int, width: Int) {
        view.layoutParams?.apply {
            this.height = height
            this.width = width
        }
    }

    fun loadBitmapToView(context: Context,
                         uri: String,
                         into: ImageView? = null,
                         listener: BitmapImageViewTarget? = null,
                         isFitCenter: Boolean = true) {
        Glide.with(context).load(uri).asBitmap().apply {
            if (isFitCenter) this.fitCenter() else this.centerCrop()
            this.diskCacheStrategy(DiskCacheStrategy.SOURCE)
            if ((null != listener && null != into) || (null != listener && null == into))
                this.into(listener)
            else if (null == listener && null != into)
                this.into(into)
            else
                throw Error("You must input a ImageView object or a Target object!!")
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