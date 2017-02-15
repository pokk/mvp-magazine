package taiwan.no1.app.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import butterknife.bindView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerActivity

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
@PerActivity
// TODO: 2017/02/15 Add the YouTube activity to MVP activity.
class VideoActivity: YouTubeBaseActivity() {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_VIDEO_URI: String = "param_video_uri"

        /**
         * Use this factory method to create a new instance of this activity using the provided parameters.
         *
         * @return A new [Intent] for starting a VideoActivity.
         */
        fun newInstance(from: Context, uri: String): Intent = Intent(from, VideoActivity::class.java).apply {
            this.putExtras(Bundle().apply {
                this.putString(ARG_PARAM_VIDEO_URI, uri)
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
    private val argUri: String by lazy { this.intent.extras.getString(ARG_PARAM_VIDEO_URI) }

    //region Fragment lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

//        this.getComponent().inject(VideoActivity@ this)
//        this.presenter.init(VideoActivity@ this)

        this.ytpvTrailer.initialize(this.getString(R.string.youtube_api_key), object: OnInitializedListener {
            override fun onInitializationSuccess(provider: Provider, player: YouTubePlayer, wasRestored: Boolean) {
                if (!wasRestored) {
                    player.loadVideo(argUri)
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)
                }
            }

            override fun onInitializationFailure(provider: Provider, errorResult: YouTubeInitializationResult) {
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
