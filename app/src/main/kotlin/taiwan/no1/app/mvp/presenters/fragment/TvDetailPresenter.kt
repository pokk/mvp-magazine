package taiwan.no1.app.mvp.presenters.fragment

import android.view.View
import android.widget.ImageView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import rx.lang.kotlin.subscriber
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.domain.usecase.TvDetail
import taiwan.no1.app.mvp.contracts.fragment.TvDetailContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.mvp.models.tv.TvDetailModel
import taiwan.no1.app.ui.fragments.MovieGalleryFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   2/12/17
 */
class TvDetailPresenter constructor(val tvDetail: TvDetail):
        BasePresenter<TvDetailContract.View>(), TvDetailContract.Presenter {
    private var tvDetailModel: TvDetailModel? = null

    //region Presenter implementation
    override fun init(view: TvDetailContract.View) {
        super.init(view)
    }

    override fun requestListTvs(id: Int) {
        val request = TvDetail.Requests(id)
        request.fragmentLifecycle = this.view.getLifecycle()
        // If declaring [subscriber] as a variable, it won't be used again.
        this.tvDetail.execute(request, subscriber<TvDetailModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
        }.onNext {
            this.tvDetailModel = it

            this.tvDetailModel?.let {
                this.view.showTvBackdrops(this.createViewPagerViews(it.images?.backdrops.orEmpty()))
                this.view.showTvBriefInfo(it.name.orEmpty(),
                        it.status.orEmpty(),
                        it.vote_average.toString(),
                        "Season " + it.seasons?.lastOrNull()?.season_number.toString(),
                        it.episode_run_time?.get(0).toString())
                this.view.showTvDetail(it.overview.orEmpty(),
                        it.last_air_date.orEmpty(),
                        it.original_language.orEmpty(),
                        it.homepage.orEmpty(),
                        it.production_companies?.let { it.flatMap { listOf(it.name) }.joinToString("\n") }.orEmpty())
                this.view.showTvSeasons(it.seasons?.filter { 0 != it.season_number }.orEmpty().also {
                    it.forEach { it.tv_id = this.tvDetailModel?.id ?: 0 }
                })
                this.view.showTvCasts(it.credits?.cast?.filter { null != it.profile_path }.orEmpty())
                this.view.showTvCrews(it.credits?.crew?.filter { null != it.profile_path }.orEmpty())
                this.view.showRelatedTvs(it.similar?.results?.let {
                    it.map { it.apply { it.isMainView = false } }.sortedWith(compareBy({ it.first_air_date })).reversed()
                }.orEmpty())
                this.view.showTvTrailers(it.videos?.results.orEmpty())
            }
        })
    }

    override fun onResourceFinished(view: View, tag: Int) {
        view.setOnClickListener {
            val posters: List<ImageProfileModel> = this.tvDetailModel?.let {
                it.images?.posters?.filter { "en" == it.iso_639_1 }
            } ?: emptyList()

            if (posters.isNotEmpty())
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_FRAGMENT,
                                MovieGalleryFragment.newInstance(posters)),
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_TAG, tag)))
        }
    }

    override fun scrollBackdropTo(index: Int) {
        // TODO: 2/25/17 Seems stupid conditions judging.
        val maxSize: Int = this.tvDetailModel?.images?.backdrops?.size ?: 0
        if (0 >= index) {
            this.view.setLeftSlideButton(View.GONE)
            this.view.setRightSlideButton(View.VISIBLE)
        }
        else if (maxSize - 1 <= index) {
            this.view.setRightSlideButton(View.GONE)
            this.view.setLeftSlideButton(View.VISIBLE)
        }
        else {
            this.view.setLeftSlideButton(View.VISIBLE)
            this.view.setRightSlideButton(View.VISIBLE)
        }
    }

    //endregion

    /**
     * Creating a view list for view pager shows each of the view.
     *
     * @param backdrops backdrops' data.
     * @return a view of list.
     */
    private fun createViewPagerViews(backdrops: List<ImageProfileModel>): List<View> =
            backdrops.map {
                View.inflate(this.view.context(), R.layout.item_tv_backdrop, null) as ImageView
            }.also {
                it.forEachIndexed { i, imageView ->
                    this.view.showTvSingleBackdrop(TMDBConfig.BASE_IMAGE_URL + backdrops[i].file_path, imageView)
                }
            }
}
