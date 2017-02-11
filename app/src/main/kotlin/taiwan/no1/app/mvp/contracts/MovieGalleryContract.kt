package taiwan.no1.app.mvp.contracts

import android.graphics.Bitmap
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/1/17
 */

interface MovieGalleryContract {
    interface Presenter: IPresenter<View> {
        fun resizeImageToFitBackground(aspectRatio: Double, image: Bitmap)
    }

    interface View: IView, IFragmentView {
        fun showBlurBackground(image: Bitmap)
    }
}