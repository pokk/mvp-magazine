package taiwan.no1.app.mvp.contracts.fragment

import android.view.View
import android.widget.ImageView
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
        fun showTvEpisodeBackDrop(uri: String, imageview: ImageView)
    }
}