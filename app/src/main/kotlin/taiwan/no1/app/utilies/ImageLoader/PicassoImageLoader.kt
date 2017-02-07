package taiwan.no1.app.utilies.ImageLoader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/27
 */

class PicassoImageLoader(val context: Context): IImageLoader {
    override fun display(uri: String,
                         into: ImageView,
                         listener: RequestListener<String, GlideDrawable>?,
                         isFitCenter: Boolean) {
    }
}