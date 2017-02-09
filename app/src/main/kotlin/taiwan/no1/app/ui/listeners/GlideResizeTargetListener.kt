package taiwan.no1.app.ui.listeners

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   2/9/17
 */


open class GlideResizeTargetListener constructor(imageView: ImageView, val resizeView: View):
        BitmapImageViewTarget(imageView) {
    override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
        super.onResourceReady(resource, glideAnimation)

        ViewUtils.resizeView(this.resizeView, resource.height, resource.width)
    }
}