package taiwan.no1.app.utilies.ImageLoader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * Glide of third-party library.
 *
 * @author  jieyi
 * @since   1/19/17
 */

class GlideImageLoader(val context: Context): IImageLoader {
    private val loader: RequestManager by lazy { Glide.with(this.context.applicationContext) }

    override fun display(uri: String, into: ImageView?, listener: BitmapImageViewTarget?, isFitCenter: Boolean) {
        this.loader.load(uri).asBitmap().also {
            if (isFitCenter) it.fitCenter() else it.centerCrop()
            it.diskCacheStrategy(DiskCacheStrategy.SOURCE)
            if ((null != listener && null != into) || (null != listener && null == into))
                it.into(listener)
            else if (null == listener && null != into)
                it.into(into)
            else
                throw Error("You must input a ImageView object or a Target object!!")
        }
    }
}