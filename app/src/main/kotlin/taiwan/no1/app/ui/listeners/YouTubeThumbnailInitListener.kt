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
    override fun onInitializationSuccess(thumbnailView: YouTubeThumbnailView, loader: YouTubeThumbnailLoader) {
    }

    override fun onInitializationFailure(thumbnailView: YouTubeThumbnailView, result: YouTubeInitializationResult) {
    }
}