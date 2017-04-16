package taiwan.no1.app.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import butterknife.bindView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.jakewharton.rxbinding.widget.changes
import taiwan.no1.app.R
import taiwan.no1.app.ui.listeners.YouTubePlaybackEventListener
import taiwan.no1.app.ui.listeners.YouTubePlayerInitListener
import taiwan.no1.app.ui.listeners.YouTubePlayerStateChangeListener

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
class VideoActivity: YouTubeBaseActivity() {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_YOUTUBE_KEY: String = "param_youtube_key"

        /**
         * Use this factory method to create a new instance of this activity using the provided parameters.
         *
         * @return A new [Intent] for starting a VideoActivity.
         */
        fun newInstance(from: Context, youtubeKey: String): Intent = Intent(from, VideoActivity::class.java).apply {
            this.putExtras(Bundle().apply {
                this.putString(ARG_PARAM_YOUTUBE_KEY, youtubeKey)
            })
        }
    }
    //endregion

    //region View variables
    private val ytpvTrailer by bindView<YouTubePlayerView>(R.id.ytpv_trailer)
    private val llController by bindView<LinearLayout>(R.id.ll_video_control)
    private val ibPlay by bindView<ImageButton>(R.id.ib_play_video)
    private val ibPause by bindView<ImageButton>(R.id.ib_pause_video)
    private val sbSeekTime by bindView<SeekBar>(R.id.sb_video)
    private val tvTime by bindView<TextView>(R.id.tv_play_time)
    //endregion

    // Get the arguments from the bundle here.
    private val argYoutubeKey: String by lazy { this.intent.extras.getString(ARG_PARAM_YOUTUBE_KEY) }
    private var youtubePlayer: YouTubePlayer? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        this.ytpvTrailer.initialize(this.getString(R.string.youtube_api_key),
                YouTubePlayerInitListener(argYoutubeKey).onSuccess { _, player, wasRestored ->
                    this@VideoActivity.youtubePlayer = player
                    this@VideoActivity.displayCurrentTime(player)
                    this@VideoActivity.youtubePlayer?.let {
                        if (!wasRestored) {
                            it.loadVideo(this.youTubeKey)
                            it.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                        }

                        // Add listeners to YouTubePlayer instance.
                        it.setPlayerStateChangeListener(YouTubePlayerStateChangeListener().onVideoStarted {
                            this@VideoActivity.youtubePlayer?.let { displayCurrentTime(it) }
                        })
                        it.setPlaybackEventListener(YouTubePlaybackEventListener().onPlaying {
                            this@VideoActivity.youtubePlayer?.let { displayCurrentTime(it) }
                        })
                    }
                })

        // NOTE: We don't use it currently. Mainly using default controller by YouTube.
        // NOTE: http://stacktips.com/tutorials/android/how-to-customize-youtubeplayer-controls-in-android.
        this.ytpvTrailer.setOnClickListener {
            if (View.VISIBLE == this.llController.visibility)
                this.llController.visibility = View.GONE
            else if (View.GONE == this.llController.visibility)
                this.llController.visibility = View.VISIBLE
        }

        this.sbSeekTime.changes().subscribe { progress ->
            this.youtubePlayer?.let {
                val lengthPlayed = it.durationMillis * progress / 100
                it.seekToMillis(lengthPlayed)
            }
        }
        View.OnClickListener { view ->
            this.youtubePlayer?.let {
                when (view) {
                    this.ibPlay -> {
                        if (!it.isPlaying)
                            it.play()
                    }
                    this.ibPause -> {
                        if (it.isPlaying)
                            it.pause()
                    }
                }
            }
        }.let {
            this.ibPlay.setOnClickListener(it)
            this.ibPause.setOnClickListener(it)
        }
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.youtubePlayer = null

        super.onDestroy()
    }
    //endregion

    private fun displayCurrentTime(player: YouTubePlayer) {
        val formattedTime = formatTime(player.durationMillis - player.currentTimeMillis)
        this.tvTime.text = formattedTime
    }

    private fun formatTime(millis: Int): String {
        val seconds = millis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return (if (hours == 0)
            "--:"
        else
            hours.toString() + ":") + String.format("%02d:%02d", minutes % 60, seconds % 60)
    }
}
