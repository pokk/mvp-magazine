package taiwan.no1.app.mvp.contracts

import android.graphics.Bitmap
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import taiwan.no1.app.mvp.models.ImageInfoModel
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
        fun updateISAspectRatio(ratio: Double)
        fun updateIsFirstImg(isFinished: Boolean)
        fun updateOldItemIndex(oldIndex: Int)
        fun updatePosters(moviePosters: List<ImageInfoModel>)
        fun updatePageOfNumber(currentNum: Int = 0)
        fun resizeImageToFitBackground(image: Bitmap)
        fun attachBackgroundFrom(hicvp: HorizontalInfiniteCycleViewPager)
        fun findViewPagerItem(hicvp: HorizontalInfiniteCycleViewPager, index: Int): android.view.View?
        fun extractBitmap(view: android.view.View?, index: Int): Bitmap?
    }

    interface View: IView, IFragmentView {
        fun showPosters(moviePosters: List<ImageInfoModel>)
        fun showCurrentNumOfPosters(total: String)
        fun showBlurBackground(image: Bitmap)
    }
}