package taiwan.no1.app.mvp.presenters

import taiwan.no1.app.mvp.contracts.MovieGalleryContract

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/1/17
 */
class MovieGalleryPresenter: BasePresenter<MovieGalleryContract.View>(), MovieGalleryContract.Presenter {
    //region View implementation
    override fun init(view: MovieGalleryContract.View) {
        super.init(view)
    }
    //endregion
}