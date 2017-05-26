package taiwan.no1.app.mvp.presenters.fragment

import android.view.View
import android.widget.ImageView
import com.devrapid.kotlinknifer.AppLog
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import rx.lang.kotlin.subscriber
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.domain.usecase.TvEpisodeDetail
import taiwan.no1.app.mvp.contracts.fragment.TvEpisodeContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.ui.fragments.MovieGalleryFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment

/**
 *
 * @author  Jieyi
 * @since   3/6/17
 */
class TvEpisodePresenter constructor(val tvEpisode: TvEpisodeDetail):
        BasePresenter<TvEpisodeContract.View>(), TvEpisodeContract.Presenter {
    //region View implementation
    override fun init(view: TvEpisodeContract.View) {
        super.init(view)
    }

    private var tvEpisodesModel: TvEpisodesModel? = null

    override fun requestTvEpisodeDetail(id: Int, seasonNum: Int, episodeNum: Int) {
        // This is exception, because we've had the episode information.
        this.view.showTvEpisodeInfo()
        val request = TvEpisodeDetail.Requests(id, seasonNum, episodeNum)
        request.fragmentLifecycle = this.view.getLifecycle()
        this.tvEpisode.execute(request, subscriber<TvEpisodesModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
            this.view.showRetry()
        }.onNext {
            this.tvEpisodesModel = it

            this.tvEpisodesModel.let {
                this.view.showTvEpisodeImages(this.createViewPagerViews(it?.images?.stills.orEmpty()))
                this.view.showTvEpisodeCasts(it?.credits?.cast?.filter { null != it.profile_path }.orEmpty())
                this.view.showTvEpisodeCrews(it?.credits?.crew?.filter { null != it.profile_path }.orEmpty())
                this.view.showTvEpisodeTrailers(it?.videos?.results.orEmpty())
            }
        }.onCompleted {
            this.view.hideLoading()
        })
    }

    private fun createViewPagerViews(backdrops: List<ImageProfileModel>): List<View> =
            backdrops.map {
                View.inflate(this.view.context(), R.layout.item_tv_backdrop, null) as ImageView
            }.also {
                it.forEachIndexed { i, imageView ->
                    this.view.showTvEpisodeBackDrop(TMDBConfig.BASE_IMAGE_URL + backdrops[i].file_path, imageView)
                }
            }

    //endregion
    override fun onResourceFinished(imageview: ImageView, argFromFragment: Int) {
        imageview.setOnClickListener {
            val posters: List<ImageProfileModel> = this.tvEpisodesModel?.let {
                it.images?.stills?.filter { "en" == it.iso_639_1 }
            } ?: emptyList()

            if (posters.isNotEmpty())
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_FRAGMENT,
                                MovieGalleryFragment.newInstance(posters)),
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_TAG, argFromFragment)))
        }
    }
}
