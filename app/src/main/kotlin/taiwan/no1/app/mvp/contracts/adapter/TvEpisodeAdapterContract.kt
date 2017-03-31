package taiwan.no1.app.mvp.contracts.adapter

import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.mvp.presenters.IAdapterPresenter
import taiwan.no1.app.mvp.views.IViewHolder

/**
 * This specifies the contract between the [IAdapterPresenter] and the [IViewHolder].
 *
 * @author  Jieyi
 * @since   3/3/17
 */

interface TvEpisodeAdapterContract {
    interface Presenter: IAdapterPresenter<View, TvEpisodesModel>

    interface View: IViewHolder {
        fun showEpisodeNumber(episodeNumber: String)
        fun showEpisodeAirDate(episodeAirDate: String)
        fun showEpisodeTitle(episodeTitle: String)
    }
}