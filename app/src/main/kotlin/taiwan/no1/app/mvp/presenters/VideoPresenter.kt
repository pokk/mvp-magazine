package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.VideoContract

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
class VideoPresenter: BasePresenter<VideoContract.View>(), VideoContract.Presenter {
    //region View implementation
    override fun init(view: VideoContract.View) {
        super.init(view)
    }
    //endregion
}
