package taiwan.no1.app.mvp.presenters.adapter

import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.MovieCrewAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.MovieCrewAdapterContract.View
import taiwan.no1.app.mvp.models.FilmCastsModel

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieCrewAdapterPresenter: BaseAdapterPresenter<View, FilmCastsModel.CrewBean>(), Presenter {
    override fun init(viewHolder: View, model: FilmCastsModel.CrewBean) {
        super.init(viewHolder, model)

        this.viewHolder.showCrewProfilePhoto(BASE_IMAGE_URL + this.model.profile_path)
        this.viewHolder.showCrewCharacter(this.model.job.orEmpty())
        this.viewHolder.showCrewName(this.model.name.orEmpty())
    }
}