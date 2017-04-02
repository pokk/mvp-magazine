package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.TvSeasonContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.ui.adapter.itemdecorator.MovieVerticalItemDecorator
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 *
 * @author Jieyi
 * @since 3/6/17
 */

@PerFragment
class TvSeasonFragment: BaseFragment(), TvSeasonContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_TV_SEASON_INFO: String = "param_tv_season_info"
        private const val ARG_PARAM_FROM_ID: String = "param_tv_from_fragment"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TvDetailFragment.
         */
        fun newInstance(seasonsModel: TvSeasonsModel, from: Int): TvSeasonFragment =
                TvSeasonFragment().apply {
                    this.arguments = Bundle().apply {
                        this.putParcelable(ARG_PARAM_TV_SEASON_INFO, seasonsModel)
                        this.putInt(ARG_PARAM_FROM_ID, from)
                    }
                }
    }
    //endregion

    @Inject
    lateinit var presenter: TvSeasonContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val ivDropPoster by bindView<ImageView>(R.id.iv_backdrop)
    private val vTopBar by bindView<View>(R.id.v_top_bar)
    private val tvDramaTitle by bindView<TextView>(R.id.tv_title)
    private val tvSeason by bindView<TextView>(R.id.tv_season)
    private val stubIntro by bindView<ViewStub>(R.id.stub_introduction)
    private val stubCasts by bindView<ViewStub>(R.id.stub_casts)
    private val tvCastsTitle by bindView<TextView>(R.id.tv_cast_title)
    private val stubCrews by bindView<ViewStub>(R.id.stub_crews)
    private val tvCrewsTitle by bindView<TextView>(R.id.tv_crew_title)
    private val stubTrailer by bindView<ViewStub>(R.id.stub_trailer)
    private val tvTrailersTitle by bindView<TextView>(R.id.tv_trailer_title)
    private val stubEpisodes by bindView<ViewStub>(R.id.stub_episodes)
    private val tvOverview by bindView<TextView>(R.id.tv_overview)
    private val rvCasts by bindView<RecyclerView>(R.id.rv_casts)
    private val rvCrews by bindView<RecyclerView>(R.id.rv_crews)
    private val rvTrailer by bindView<RecyclerView>(R.id.rv_trailer)
    private val rvEpisodes by bindView<RecyclerView>(R.id.rv_episode)
    //endregion

    // Get the arguments from the bundle here.
    private val argTvSeasonInfo: TvSeasonsModel by lazy {
        this.arguments.getParcelable<TvSeasonsModel>(ARG_PARAM_TV_SEASON_INFO)
    }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_FROM_ID) }

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
        this.getComponent(FragmentComponent::class.java).inject(TvSeasonFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tv_season_detail

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TvSeasonFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.imageLoader.display(TMDBConfig.BASE_IMAGE_URL + this.argTvSeasonInfo.poster_path,
                listener = object: BitmapImageViewTarget(ivDropPoster) {
                    override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                        super.onResourceReady(resource, glideAnimation)
                    }
                },
                isFitCenter = false)
        this.vTopBar.bringToFront()
        this.tvDramaTitle.text = this.argTvSeasonInfo.tv_name
        this.tvSeason.text = "( S.${this.argTvSeasonInfo.season_number} / EP.${this.argTvSeasonInfo.episode_count} )"

        this.presenter.requestSeasonDetail(this.argTvSeasonInfo.tv_id, this.argTvSeasonInfo.season_number)
    }
    //endregion

    //region View implementations
    override fun showTvOverview(overview: String) {
        // Inflate the introduction section.
        this.showViewStub(this.stubIntro, { this.tvOverview.text = overview })
    }

    override fun showTvCasts(casts: List<FilmCastsModel.CastBean>) {
        // Inflate the cast section.
        if (casts.isNotEmpty()) {
            this.showViewStub(this.stubCasts, { this.showCardItems(this.rvCasts, casts) })
            this.tvCastsTitle.setTextColor(Color.rgb(248, 247, 251))
        }
    }

    override fun showTvCrews(crews: List<FilmCastsModel.CrewBean>) {
        // Inflate the crew section.
        if (crews.isNotEmpty()) {
            this.showViewStub(this.stubCrews, { this.showCardItems(this.rvCrews, crews) })
            this.tvCrewsTitle.setTextColor(Color.rgb(248, 247, 251))
        }
    }

    override fun showTvEpisodes(episodes: List<TvEpisodesModel>) {
        // Inflate the trailer movieList section.
        if (episodes.isNotEmpty())
            this.showViewStub(this.stubEpisodes, { this.showCardItems(this.rvEpisodes, episodes) })
    }

    override fun showTvTrailers(trailers: List<FilmVideoModel>) {
        // Inflate the trailer movieList section.
        if (trailers.isNotEmpty()) {
            this.showViewStub(this.stubTrailer, { this.showCardItems(this.rvTrailer, trailers) })
            this.tvTrailersTitle.setTextColor(Color.rgb(248, 247, 251))
        }
    }
    //endregion

    private fun <T: IVisitable> showCardItems(recyclerView: RecyclerView, list: List<T>) {
        recyclerView.apply {
            // Only episode list is vertical layout.
            if (list.isNotEmpty() && list[0] is TvEpisodesModel) {
                this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                this.addItemDecoration(MovieVerticalItemDecorator(20, 60))
                LinearSnapHelper().also { it.attachToRecyclerView(this) }
            }
            else {
                this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                this.addItemDecoration(MovieHorizontalItemDecorator(30))
            }
            this.adapter = CommonRecyclerAdapter(list, argFromFragment)
        }
    }
}
