package taiwan.no1.app.ui.listeners

import com.google.android.youtube.player.YouTubePlayer

/**
 *
 * @author  jieyi
 * @since   4/16/17
 */

typealias YouTubePlaybackEmptyFun = YouTubePlaybackEventListener.() -> Unit

class YouTubePlaybackEventListener: YouTubePlayer.PlaybackEventListener {
    private var seekToFunction: (YouTubePlaybackEventListener.(position: Int) -> Unit)? = null
    private var bufferingFunction: (YouTubePlaybackEventListener.(isBuffering: Boolean) -> Unit)? = null
    private var playingFunction: YouTubePlaybackEmptyFun? = null
    private var stoppedFunction: YouTubePlaybackEmptyFun? = null
    private var pausedFunction: YouTubePlaybackEmptyFun? = null

    override fun onSeekTo(p0: Int) = this.seekToFunction?.let { it(p0) } ?: Unit

    override fun onBuffering(p0: Boolean) = this.bufferingFunction?.let { it(p0) } ?: Unit

    override fun onPlaying() = this.playingFunction?.let { it() } ?: Unit

    override fun onStopped() = this.stoppedFunction?.let { it() } ?: Unit

    override fun onPaused() = this.pausedFunction?.let { it() } ?: Unit

    fun onSeekTo(onSeekToFunction: YouTubePlaybackEventListener.(position: Int) -> Unit): YouTubePlaybackEventListener =
            this.also { it.seekToFunction = onSeekToFunction }

    fun onBuffering(onBufferingFunction: YouTubePlaybackEventListener.(isBuffering: Boolean) -> Unit): YouTubePlaybackEventListener =
            this.also { it.bufferingFunction = onBufferingFunction }

    fun onPlaying(onPlayingFunction: YouTubePlaybackEmptyFun): YouTubePlaybackEventListener =
            this.also { it.playingFunction = onPlayingFunction }

    fun onStopped(onStoppedFunction: YouTubePlaybackEmptyFun): YouTubePlaybackEventListener =
            this.also { it.stoppedFunction = onStoppedFunction }

    fun onPaused(onPausedFunction: YouTubePlaybackEmptyFun): YouTubePlaybackEventListener =
            this.also { it.pausedFunction = onPausedFunction }
}
