package taiwan.no1.app.utilies.ImageLoader

import android.content.Context

/**
 * Third-party image loader's factory.
 *
 * @author  jieyi
 * @since   1/19/17
 */

class ImageLoaderFactory {
    enum class Type {
        GLIDE, PICASSO
    }

    companion object {
        @JvmStatic fun getRequest(context: Context, type: Type = Type.GLIDE): IImageLoader =
                when (type) {
                    Type.GLIDE -> GlideImageLoader(context)
                    else -> GlideImageLoader(context)
                }
    }
}