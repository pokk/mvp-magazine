package taiwan.no1.app.ui.listeners

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.google.android.youtube.player.YouTubeThumbnailView.OnInitializedListener

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

open class YouTubeThumbnailInitListener(val youTubeKey: String): OnInitializedListener {
    // XXX: 4/14/17 Here may memory leak!?! ** Search that if lambda function doesn't be released, it'll memory leak or not. **
    // More like Kotlin style Chain style.
    var successFunction: (YouTubeThumbnailInitListener.(thumbnailView: YouTubeThumbnailView, loader: YouTubeThumbnailLoader) -> Unit)? = null
    var failureFunction: (YouTubeThumbnailInitListener.(thumbnailView: YouTubeThumbnailView, result: YouTubeInitializationResult) -> Unit)? = null

    override fun onInitializationSuccess(thumbnailView: YouTubeThumbnailView, loader: YouTubeThumbnailLoader) =
            this.successFunction?.let { it(thumbnailView, loader) } ?: Unit

    override fun onInitializationFailure(thumbnailView: YouTubeThumbnailView, result: YouTubeInitializationResult) =
            this.failureFunction?.let { it(thumbnailView, result) } ?: Unit

    fun onSuccess(onSuccessFunction: YouTubeThumbnailInitListener.(thumbnailView: YouTubeThumbnailView, loader: YouTubeThumbnailLoader) -> Unit):
            YouTubeThumbnailInitListener = this.also { it.successFunction = onSuccessFunction }

    fun onFailure(onFailureFunction: YouTubeThumbnailInitListener.(thumbnailView: YouTubeThumbnailView, result: YouTubeInitializationResult) -> Unit):
            YouTubeThumbnailInitListener = this.also { it.failureFunction = onFailureFunction }
}