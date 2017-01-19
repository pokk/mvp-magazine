package taiwan.no1.app.utilies.ImageLoader

import android.content.Context

/**
 * Third-party image loader's factory.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/19
 */

class ImageLoaderFactory {
    enum class Type {
        GLIDE,
    }

    companion object {
        @JvmStatic fun getRequest(context: Context, type: Type = Type.GLIDE): IImageLoader =
                when (type) {
                    Type.GLIDE -> GlideImageLoader(context)
                    else -> GlideImageLoader(context)
                }
    }
}