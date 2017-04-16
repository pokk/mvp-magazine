package taiwan.no1.app.mvp.presenters.adapter

import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.TvEpisodeAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvEpisodeAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel

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
}
