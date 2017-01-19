package taiwan.no1.app.utilies.ImageLoader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener

/**
 * Glide of third-party library.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/19
 */

class GlideImageLoader(val context: Context): IImageLoader {
    private val loader: RequestManager by lazy { Glide.with(this.context.applicationContext) }

    override fun display(uri: String,
                         into: ImageView,
                         listener: RequestListener<String, GlideDrawable>?,
                         isFitCenter: Boolean) {
        this.loader.load(uri).apply {
            if (isFitCenter) fitCenter() else centerCrop()
            diskCacheStrategy(DiskCacheStrategy.SOURCE)
            listener?.let { listener(listener) }
            into(into)
        }
    }
}