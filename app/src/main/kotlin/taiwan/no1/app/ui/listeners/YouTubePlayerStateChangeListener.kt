package taiwan.no1.app.ui.listeners

import com.google.android.youtube.player.YouTubePlayer

/**
 *
 * @author  jieyi
 * @since   4/16/17
 */

typealias YouTubePlayerState = YouTubePlayerStateChangeListener.() -> Unit

class YouTubePlayerStateChangeListener: YouTubePlayer.PlayerStateChangeListener {
    private var adStartedFunction: YouTubePlayerState? = null
    private var loadingFunction: YouTubePlayerState? = null
    private var videoStartedFunction: YouTubePlayerState? = null
    private var loadedFunction: (YouTubePlayerStateChangeListener.(name: String?) -> Unit)? = null
    private var videoEndedFunction: YouTubePlayerState? = null
    private var errorFunction: (YouTubePlayerStateChangeListener.(error: YouTubePlayer.ErrorReason?) -> Unit)? = null

    override fun onAdStarted() = this.adStartedFunction?.let { it() } ?: Unit

    override fun onLoading() = this.loadingFunction?.let { it() } ?: Unit

    override fun onVideoStarted() = this.videoStartedFunction?.let { it() } ?: Unit

    override fun onLoaded(p0: String?) = this.loadedFunction?.let { it(p0) } ?: Unit

    override fun onVideoEnded() = this.videoEndedFunction?.let { it() } ?: Unit

    override fun onError(p0: YouTubePlayer.ErrorReason?) = this.errorFunction?.let { it(p0) } ?: Unit

    fun onAdStarted(onAdStartedFunction: YouTubePlayerState): YouTubePlayerStateChangeListener =
            this.also { it.adStartedFunction = onAdStartedFunction }

    fun onLoading(onLoadingFunction: YouTubePlayerState): YouTubePlayerStateChangeListener =
            this.also { it.loadingFunction = onLoadingFunction }

    fun onVideoStarted(onVideoStartedFunction: YouTubePlayerState): YouTubePlayerStateChangeListener =
            this.also { it.videoStartedFunction = onVideoStartedFunction }

    fun onLoaded(onLoadedFunction: (YouTubePlayerStateChangeListener.(name: String?) -> Unit)): YouTubePlayerStateChangeListener =
            this.also { it.loadedFunction = onLoadedFunction }

    fun onVideoEnded(onVideoEndedFunction: YouTubePlayerState): YouTubePlayerStateChangeListener =
            this.also { it.videoEndedFunction = onVideoEndedFunction }

    fun onError(onErrorFunction: (YouTubePlayerStateChangeListener.(error: YouTubePlayer.ErrorReason?) -> Unit)): YouTubePlayerStateChangeListener =
            this.also { it.errorFunction = onErrorFunction }
}