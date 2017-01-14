package taiwan.no1.app.ui.listeners

import android.view.View
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.target.Target
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */

class GlideResizeRequestListener constructor(val view: View): GlideCustomRequestListener() {
    override fun onResourceReady(resource: GlideDrawable,
                                 model: String,
                                 target: Target<GlideDrawable>,
                                 isFromMemoryCache: Boolean,
                                 isFirstResource: Boolean): Boolean {
        ViewUtils.resizeView(this.view, resource.intrinsicHeight, resource.intrinsicWidth)
        return super.onResourceReady(resource, model, target, isFromMemoryCache, isFirstResource)
    }
}
