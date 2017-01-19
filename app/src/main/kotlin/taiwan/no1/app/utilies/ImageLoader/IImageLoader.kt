package taiwan.no1.app.utilies.ImageLoader

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener

/**
 * Third-party image loader display methods' interface.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/19
 */
interface IImageLoader {
    fun display(uri: String,
                into: ImageView,
                listener: RequestListener<String, GlideDrawable>? = null,
                isFitCenter: Boolean = true)
}