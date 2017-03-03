package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.contracts.adapter.TvSeasonAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvSeasonAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
import taiwan.no1.app.ui.fragments.TvDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment

/**
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TvSeasonAdapterPresenter: BaseAdapterPresenter<View, TvSeasonsModel>(), Presenter {
    override fun init(viewHolder: View, model: TvSeasonsModel) {
        super.init(viewHolder, model)

        this.viewHolder.showTvPoster(TMDBConfig.BASE_IMAGE_URL + this.model.poster_path)
//        this.viewHolder.showTvEpisodeNumber()
//        this.viewHolder.showTvSeasonNumber()
//        this.viewHolder.showTvAirDate()
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_FRAGMENT,
                        TvDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_TAG, tag)))
    }

    override fun onPosterResourceFinished(bitmap: Bitmap) {
    }
} 