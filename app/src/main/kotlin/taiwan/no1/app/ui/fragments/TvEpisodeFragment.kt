package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.TvEpisodeContract
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.BackdropPagerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   3/6/17
 */
@PerFragment
class TvEpisodeFragment: BaseFragment(), TvEpisodeContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_TV_ID: String = "param_tv_id"
        private const val ARG_PARAM_SEASON_NUMBER: String = "param_season_number"
        private const val ARG_PARAM_EPISODE_NUMBER: String = "param_episode_number"
        private const val ARG_PARAM_TV_FROM_FRAGMENT: String = "param_from_fragment"
        private const val ARG_PARAM_TV_EPISODE_INFO: String = "param_tv_episode_info"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [TvEpisodeFragment].
         */
        fun newInstance(episodeModel: TvEpisodesModel, from: Int): TvEpisodeFragment =
                TvEpisodeFragment().also {
                    it.arguments = Bundle().also {
                        it.putParcelable(ARG_PARAM_TV_EPISODE_INFO, episodeModel)
                        it.putInt(ARG_PARAM_TV_ID, from)
                    }
                }
    }
    //endregion

    @Inject
    lateinit var presenter: TvEpisodeContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val tvEpisodeTitle by bindView<TextView>(R.id.tv_episode_title)
    private val tvAirDate by bindView<TextView>(R.id.tv_air_date)
    private val tvOverview by bindView<TextView>(R.id.tv_overview)
    private val vp_drop_poster by bindView<ViewPager>(R.id.vp_drop_poster)
    //endregion

    // Get the arguments from the bundle here.
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_TV_FROM_FRAGMENT) }
    private val argEpisodeInfo: TvEpisodesModel by lazy {
        this.arguments.getParcelable<TvEpisodesModel>(ARG_PARAM_TV_EPISODE_INFO)
    }

    //region Fragment lifecycle
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

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java).inject(TvEpisodeFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tv_episode_detail

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TvEpisodeFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.presenter.requestTvEpisodeDetail(argEpisodeInfo.tv_id,
                                              argEpisodeInfo.season_number, argEpisodeInfo.episode_number)
    }

    //endregion

    override fun showTvEpisodeInfo() {
        this.tvEpisodeTitle.text = argEpisodeInfo.name
        this.tvAirDate.text = argEpisodeInfo.air_date
        this.tvOverview.text = argEpisodeInfo.overview
    }

    override fun showTvEpisodeImages(list: List<View>) {
        this.vp_drop_poster.adapter = BackdropPagerAdapter(list)
    }

    override fun showTvEpisodes(episodes: List<TvEpisodesModel>) {
    }

    override fun showTvEpisodeBackDrop(uri: String, imageview: ImageView) {
        this.imageLoader.display(uri, listener = object: BitmapImageViewTarget(imageview) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@TvEpisodeFragment.presenter.onResourceFinished(imageview, this@TvEpisodeFragment.argFromFragment)
                super.onResourceReady(resource, glideAnimation)
            }
        }, isFitCenter = false)
    }
}
