package taiwan.no1.app.mvp.contracts.fragment

import android.widget.ImageView
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

interface TvEpisodeContract {
    interface Presenter: IPresenter<View> {
        fun requestTvEpisodeDetail(id: Int, seasonNum: Int, episodeNum: Int)
        fun onResourceFinished(imageview: ImageView, argFromFragment: Int)
    }

    interface View: IView, IFragmentView {
        fun showTvEpisodeInfo()
        fun showTvEpisodeImages(list: List<android.view.View>)
        fun showTvEpisodes(episodes: List<TvEpisodesModel>)
        fun showTvEpisodeCasts(casts: List<FilmCastsModel.CastBean>)
        fun showTvEpisodeCrews(crews: List<FilmCastsModel.CrewBean>)
        fun showTvEpisodeTrailers(trailers: List<FilmVideoModel>)
        fun showTvEpisodeBackDrop(uri: String, imageview: ImageView)
    }
}