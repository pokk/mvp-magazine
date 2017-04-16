package taiwan.no1.app.mvp.presenters.adapter

import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.TvSeasonAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvSeasonAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
import taiwan.no1.app.ui.fragments.TvSeasonFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TvSeasonAdapterPresenter: BaseAdapterPresenter<View, TvSeasonsModel>(), Presenter {
    override fun init(viewHolder: View, model: TvSeasonsModel) {
        super.init(viewHolder, model)

        this.viewHolder.also {
            it.showTvPoster(BASE_IMAGE_URL + this.model.poster_path)
            it.showTvEpisodeNumber(this.model.episode_count.toString())
            it.showTvSeasonNumber("Season " + this.model.season_number.toString())
            it.showTvAirDate(this.model.air_date.orEmpty())
        }
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, TvSeasonFragment.newInstance(this.model, tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
    }
}
