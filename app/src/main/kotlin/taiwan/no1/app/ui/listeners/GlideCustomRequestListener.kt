package taiwan.no1.app.ui.listeners

import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * @author Jieyi
 * *
 * @since 1/15/17
 */

open class GlideCustomRequestListener: RequestListener<String, GlideDrawable> {
    override fun onException(e: Exception,
                             model: String,
                             target: Target<GlideDrawable>,
                             isFirstResource: Boolean): Boolean = false

    override fun onResourceReady(resource: GlideDrawable,
                                 model: String,
                                 target: Target<GlideDrawable>,
                                 isFromMemoryCache: Boolean,
                                 isFirstResource: Boolean): Boolean = false
}