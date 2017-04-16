package taiwan.no1.app.ui.listeners

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider

/**
 * @author  Jieyi
 * @since   2/21/17
 */

open class YouTubePlayerInitListener(val youTubeKey: String): OnInitializedListener {
    private var successFunction: (YouTubePlayerInitListener.(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) -> Unit)? = null
    private var failureFunction: (YouTubePlayerInitListener.(provider: Provider, result: YouTubeInitializationResult) -> Unit)? = null

    override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) =
            this.successFunction?.let { it(provider, player, wasRestored) } ?: Unit

    override fun onInitializationFailure(provider: Provider, result: YouTubeInitializationResult) =
            this.failureFunction?.let { it(provider, result) } ?: Unit

    fun onSuccess(onSuccessFunction: YouTubePlayerInitListener.(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) -> Unit):
            YouTubePlayerInitListener = this.also { it.successFunction = onSuccessFunction }

    fun onFailure(onFailureFunction: YouTubePlayerInitListener.(provider: Provider, result: YouTubeInitializationResult) -> Unit):
            YouTubePlayerInitListener = this.also { it.failureFunction = onFailureFunction }
}
