package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.MovieMainContract
import javax.inject.Inject

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/12
 */
class MovieMainPresenter @Inject constructor(): BasePresenter<MovieMainContract.View>(),
        MovieMainContract.Presenter {
    //region View implementation
    override fun init(view: MovieMainContract.View) {
        super.init(view)
    }
    //endregion
}