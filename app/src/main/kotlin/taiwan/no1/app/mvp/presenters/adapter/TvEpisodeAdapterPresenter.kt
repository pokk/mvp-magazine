package taiwan.no1.app.mvp.presenters.adapter

import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.TvEpisodeAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvEpisodeAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.ui.fragments.TvEpisodeFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TvEpisodeAdapterPresenter: BaseAdapterPresenter<View, TvEpisodesModel>(), Presenter {
    override fun init(viewHolder: View, model: TvEpisodesModel) {
        super.init(viewHolder, model)

        this.viewHolder.also {
            it.showEpisodeThumbnail(BASE_IMAGE_URL + this.model.still_path)
            it.showEpisodeNumber("Episode ${this.model.episode_number}")
            it.showEpisodeAirDate(this.model.air_date.orEmpty())
            it.showEpisodeTitle(this.model.name.orEmpty())
        }
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, TvEpisodeFragment.newInstance(
                        this.model, tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
    }
}
