package taiwan.no1.app.utilies

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener

/**
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

    fun loadImageToView(context: Context,
                        uri: String,
                        into: ImageView,
                        listener: RequestListener<String, GlideDrawable>? = null,
                        isFitCenter: Boolean = true) {
        Glide.with(context).
                load(uri).apply {
            if (isFitCenter) fitCenter() else centerCrop()
            diskCacheStrategy(DiskCacheStrategy.SOURCE)
            listener?.let { listener(listener) }
            into(into)
        }
    }
}