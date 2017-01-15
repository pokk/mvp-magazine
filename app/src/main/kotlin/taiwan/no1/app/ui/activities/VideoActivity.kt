package taiwan.no1.app.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.mvp.contracts.VideoContract
import taiwan.no1.app.ui.BaseActivity
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
@PerActivity
class VideoActivity: BaseActivity(), VideoContract.View {
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

    @Inject
    lateinit var presenter: VideoContract.Presenter

    //region View variables
    private val vvVideo by bindView<VideoView>(R.id.vv_trailer)
    //endregion

    // Get the arguments from the bundle here.
    private val argUri: String by lazy { this.intent.extras.getString(ARG_PARAM_VIDEO_URI) }

    //region Fragment lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        this.getComponent().inject(VideoActivity@ this)
        this.presenter.init(VideoActivity@ this)

        this.vvVideo.setVideoURI(Uri.parse(this.argUri))
        this.vvVideo.start()
    }

    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion
}
