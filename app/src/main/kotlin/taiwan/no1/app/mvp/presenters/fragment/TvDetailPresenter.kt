package taiwan.no1.app.mvp.presenters.fragment

import android.view.View
import android.widget.ImageView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import rx.lang.kotlin.subscriber
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.domain.usecase.TVDetail
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
class TvDetailPresenter constructor(val tvDetail: TVDetail):
        BasePresenter<TvDetailContract.View>(), TvDetailContract.Presenter {
    private var tvDetailModel: TvDetailModel? = null

    //region Presenter implementation
    override fun init(view: TvDetailContract.View) {
        super.init(view)
    }

    override fun requestListTvs(id: Int) {
        val request = TVDetail.Requests(id)
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
                        it.last_air_date.orEmpty())
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
    //endregion

    private fun createViewPagerViews(backdrops: List<ImageProfileModel>): List<View> =
            backdrops.map {
                View.inflate(this.view.context(), R.layout.item_tv_backdrop, null) as ImageView
            }.apply {
                this.forEachIndexed { i, imageView ->
                    this@TvDetailPresenter.view.showTvSingleBackdrop(TMDBConfig.BASE_IMAGE_URL + backdrops[i].file_path,
                            imageView)

                }
            }
}
