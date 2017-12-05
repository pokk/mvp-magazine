package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.TvEpisodeContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.BackdropPagerAdapter
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   3/6/17
 */
@PerFragment
class TvEpisodeFragment : BaseFragment(), TvEpisodeContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_TV_ID: String = "param_tv_id"
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
    private val stubCasts by bindView<ViewStub>(R.id.stub_casts)
    private val stubCrews by bindView<ViewStub>(R.id.stub_crews)
    private val stubTrailer by bindView<ViewStub>(R.id.stub_trailer)
    private val rvCasts by bindView<RecyclerView>(R.id.rv_casts)
    private val tvCastTitle by bindView<TextView>(R.id.tv_cast_title)
    private val rvCrews by bindView<RecyclerView>(R.id.rv_crews)
    private val tvCrewTitle by bindView<TextView>(R.id.tv_crew_title)
    private val rvTrailer by bindView<RecyclerView>(R.id.rv_trailer)
    private val tvTrailerTitle by bindView<TextView>(R.id.tv_trailer_title)
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
        this.showLoading()
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

    override fun showTvEpisodeCasts(casts: List<FilmCastsModel.CastBean>) {
        // Inflate the cast section.
        if (casts.isNotEmpty())
            this.showViewStub(this.stubCasts, {
                this.showCardItems(this.rvCasts, casts)
                this.tvCastTitle.setTextColor(this.context.resources.getColor(R.color.recyclerview_dark_bg_title))
            })
    }

    override fun showTvEpisodeCrews(crews: List<FilmCastsModel.CrewBean>) {
        // Inflate the crew section.
        if (crews.isNotEmpty())
            this.showViewStub(this.stubCrews, {
                this.showCardItems(this.rvCrews, crews)
                this.tvCrewTitle.setTextColor(this.context.resources.getColor(R.color.recyclerview_dark_bg_title))
            })
    }

    override fun showTvEpisodeTrailers(trailers: List<FilmVideoModel>) {
        // Inflate the trailer movieList section.
        if (trailers.isNotEmpty())
            this.showViewStub(this.stubTrailer, {
                this.showCardItems(this.rvTrailer, trailers)
                this.tvTrailerTitle.setTextColor(this.context.resources.getColor(R.color.recyclerview_dark_bg_title))
            })
    }

    override fun showTvEpisodeBackDrop(uri: String, imageview: ImageView) {
        this.imageLoader.display(uri, listener = object : BitmapImageViewTarget(imageview) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@TvEpisodeFragment.presenter.onResourceFinished(imageview, this@TvEpisodeFragment.argFromFragment)
                super.onResourceReady(resource, glideAnimation)
            }
        }, isFitCenter = false)
    }

    private fun <T : IVisitable> showCardItems(recyclerView: RecyclerView, list: List<T>) {
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = CommonRecyclerAdapter(list, argFromFragment)
            this.addItemDecoration(MovieHorizontalItemDecorator(30))
        }
    }
}
