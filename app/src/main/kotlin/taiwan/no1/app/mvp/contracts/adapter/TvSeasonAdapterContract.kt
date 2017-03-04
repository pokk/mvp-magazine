package taiwan.no1.app.mvp.contracts.adapter

import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 * This specifies the contract between the [IAdapterPresenter] and the [IViewHolder].
 *
 * @author  Jieyi
 * @since   3/3/17
 */

interface TvSeasonAdapterContract {
    interface Presenter: IAdapterPresenter<View, TvSeasonsModel> {
        fun onItemClicked(tag: Int)
    }

    interface View: IViewHolder {
        fun showTvPoster(uri: String)
        fun showTvEpisodeNumber(episodeNumber: String)
        fun showTvSeasonNumber(seasonNumber: String)
        fun showTvAirDate(airDate: String)
    }
}