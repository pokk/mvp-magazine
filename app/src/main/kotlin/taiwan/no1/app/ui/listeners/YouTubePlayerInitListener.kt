package taiwan.no1.app.ui.listeners

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

open class YouTubePlayerInitListener(val youTubeKey: String): OnInitializedListener {
    override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) {
    }

    override fun onInitializationFailure(provider: Provider, result: YouTubeInitializationResult) {
    }
}