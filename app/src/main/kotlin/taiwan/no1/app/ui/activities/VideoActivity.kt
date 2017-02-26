package taiwan.no1.app.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import butterknife.bindView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.ui.listeners.YouTubePlayerInitListener

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
@PerActivity
// TODO: 2/15/17 Add the YouTube activity to MVP activity.
// FIXME: 2/26/17 We need to know the activity and fragment's lifecycle clearly.
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

//    @Inject
//    lateinit var presenter: VideoContract.Presenter

    //region View variables
    private val ytpvTrailer by bindView<YouTubePlayerView>(R.id.ytpv_trailer)
    //endregion

    // Get the arguments from the bundle here.
    private val argYoutubeKey: String by lazy { this.intent.extras.getString(ARG_PARAM_YOUTUBE_KEY) }

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

//        this.getComponent().inject(VideoActivity@ this)
//        this.presenter.init(VideoActivity@ this)

        this.ytpvTrailer.initialize(this.getString(R.string.youtube_api_key),
                object: YouTubePlayerInitListener(argYoutubeKey) {
                    override fun onInitializationSuccess(provider: Provider,
                                                         player: YouTubePlayer,
                                                         wasRestored: Boolean) {
                        if (!wasRestored) {
                            player.loadVideo(this.youTubeKey)
                            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)
                        }
                    }
                })
    }

    override fun onResume() {
        super.onResume()
//        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
//        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
//        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion
}
