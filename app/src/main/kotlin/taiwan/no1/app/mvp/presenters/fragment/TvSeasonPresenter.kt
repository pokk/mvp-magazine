package taiwan.no1.app.mvp.presenters.fragment

import com.devrapid.kotlinknifer.AppLog
import rx.lang.kotlin.subscriber
import taiwan.no1.app.domain.usecase.TvSeasonDetail
import taiwan.no1.app.mvp.contracts.fragment.TvSeasonContract
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel

/**
 * @author Jieyi
 * *
 * @since 3/6/17
 */

class TvSeasonPresenter constructor(val tvSeasonDetail: TvSeasonDetail):
        BasePresenter<TvSeasonContract.View>(), TvSeasonContract.Presenter {
    private var tvSeasonModel: TvSeasonsModel = TvSeasonsModel()

    //region Presenter implementation
    override fun init(view: TvSeasonContract.View) {
        super.init(view)
    }

    override fun requestSeasonDetail(tvId: Int, seasonNumber: Int) {
        this.view.showLoading()
        
        val request = TvSeasonDetail.Requests(tvId, seasonNumber)
        request.fragmentLifecycle = this.view.getLifecycle()
        // If declaring [subscriber] as a variable, it won't be used again.
        this.tvSeasonDetail.execute(request, subscriber<TvSeasonsModel>().onError {
            AppLog.e(it.message)
            AppLog.e(it)
            this.view.showRetry()
        }.onNext {
            this.tvSeasonModel = it

            this.view.showTvOverview(it.overview.orEmpty())
            this.view.showTvCasts(it.credits?.cast?.filter { null != it.profile_path }.orEmpty())
            this.view.showTvCrews(it.credits?.crew?.filter { null != it.profile_path }.orEmpty())
            this.view.showTvEpisodes(it.episodes.orEmpty())
            this.view.showTvTrailers(it.videos?.results.orEmpty())
        }.onCompleted { this.view.hideLoading() })
    }

    //endregion
}
