package taiwan.no1.app.mvp.presenters.adapter

import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.MovieRelatedAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.MovieRelatedAdapterContract.View
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.TvDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieRelatedAdapterPresenter: BaseAdapterPresenter<View, IVisitable>(), Presenter {
    private var filmId: Int = 0

    override fun init(viewHolder: View, model: IVisitable) {
        super.init(viewHolder, model)

        if (model is MovieBriefModel) {
            this.viewHolder.showMoviePoster(BASE_IMAGE_URL + model.poster_path)
            this.viewHolder.showMovieReleaseDate(model.release_date.orEmpty())
            this.viewHolder.showMovieTitle(model.title.orEmpty())
            this.filmId = model.id
        }
        else if (model is TvBriefModel) {
            this.viewHolder.showMoviePoster(BASE_IMAGE_URL + model.poster_path)
            this.viewHolder.showMovieReleaseDate(model.first_air_date.orEmpty())
            this.viewHolder.showMovieTitle(model.name.orEmpty())
            this.filmId = model.id
        }
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, if (model is MovieBriefModel)
                    MovieDetailFragment.newInstance(filmId.toString(), tag)
                else
                    TvDetailFragment.newInstance(filmId.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
//                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_SHARED_ELEMENTS,
//                        hashMapOf(Pair(tvRelease, tvRelease.transitionName)))))
    }
} 