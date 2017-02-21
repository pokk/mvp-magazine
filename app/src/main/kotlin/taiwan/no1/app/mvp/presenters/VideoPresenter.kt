package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.VideoContract
import taiwan.no1.app.mvp.presenters.fragment.BasePresenter

/**
 *
 * @author  Jieyi
 * @since   1/15/17
 */
class VideoPresenter: BasePresenter<VideoContract.View>(), VideoContract.Presenter {
    //region Presenter implementation
    override fun init(view: VideoContract.View) {
        super.init(view)
    }
    //endregion
}
