package taiwan.no1.app.mvp.presenters.fragment

import taiwan.no1.app.mvp.contracts.fragment.TvEpisodeContract

/**
 *
 * @author  Jieyi
 * @since   3/6/17
 */
class TvEpisodePresenter: BasePresenter<TvEpisodeContract.View>(), TvEpisodeContract.Presenter {
    //region View implementation
    override fun init(view: TvEpisodeContract.View) {
        super.init(view)
    }
    //endregion
}
