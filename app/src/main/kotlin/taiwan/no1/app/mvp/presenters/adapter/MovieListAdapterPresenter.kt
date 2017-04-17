package taiwan.no1.app.mvp.presenters.adapter

import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.MovieListAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.MovieListAdapterContract.View
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieListAdapterPresenter: BaseAdapterPresenter<View, MovieBriefModel>(), Presenter {
    override fun init(viewHolder: View, model: MovieBriefModel) {
        super.init(viewHolder, model)

        this.viewHolder.showMoviePoster(BASE_IMAGE_URL + this.model.poster_path)
        this.viewHolder.showMovieReleaseDate(this.model.release_date.orEmpty())
        this.viewHolder.showMovieTitle(this.model.title.orEmpty())
        this.viewHolder.showMovieVote("${this.model.vote_average} / 10")
        this.viewHolder.showMovieOverview(this.model.overview.orEmpty())
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, MovieDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)
        ))
    }
}