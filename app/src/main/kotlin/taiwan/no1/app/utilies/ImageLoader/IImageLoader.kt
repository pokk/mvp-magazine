package taiwan.no1.app.utilies.ImageLoader

import android.widget.ImageView
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * Third-party image loader display methods' interface.
 *
 * @author  jieyi
 * @since   1/19/17
 */
interface IImageLoader {
    fun display(uri: String,
                into: ImageView? = null,
                listener: BitmapImageViewTarget? = null,
                isFitCenter: Boolean = true)
}