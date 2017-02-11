package taiwan.no1.app.mvp.presenters

import android.graphics.Bitmap
import taiwan.no1.app.mvp.contracts.MovieGalleryContract
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */
class MovieGalleryPresenter: BasePresenter<MovieGalleryContract.View>(), MovieGalleryContract.Presenter {
    //region View implementation
    override fun init(view: MovieGalleryContract.View) {
        super.init(view)
    }

    override fun resizeImageToFitBackground(aspectRatio: Double, image: Bitmap) {
        this.view.showBlurBackground(ViewUtils.resizeImageAsRatio(aspectRatio, image))
    }
    //endregion
}
