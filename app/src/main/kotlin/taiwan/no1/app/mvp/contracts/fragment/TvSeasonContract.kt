package taiwan.no1.app.mvp.contracts.fragment

import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   3/6/17
 */

interface TvSeasonContract {
    interface Presenter: IPresenter<View> {
        fun requestSeasonDetail(tvId: Int, seasonNumber: Int)
    }

    interface View: IView, IFragmentView {
        fun showTvCasts(casts: List<FilmCastsModel.CastBean>)
        fun showTvCrews(crews: List<FilmCastsModel.CrewBean>)
        fun showTvEpisodes(episodes: List<TvEpisodesModel>)
        fun showTvTrailers(trailers: List<FilmVideoModel>)
    }
}