package taiwan.no1.app.mvp.contracts.fragment

import android.graphics.Bitmap
import android.support.v7.widget.CardView
import android.widget.ImageView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import taiwan.no1.app.mvp.models.ImageProfileModel
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
        fun updateOldItemIndex(oldIndex: Int)
        fun updatePosters(posters: List<ImageProfileModel>)
        fun updatePageOfNumber(currentNum: Int = 0)
        fun onResourceFinished(hicvp: HorizontalInfiniteCycleViewPager, isRatio: Double, position: Int)
        fun attachBackgroundFrom(hicvp: HorizontalInfiniteCycleViewPager, isRatio: Double)
        fun extractBitmap(hicvp: HorizontalInfiniteCycleViewPager, index: Int): Bitmap?
    }

    interface View: IView, IFragmentView {
        fun showPosters(viewList: List<android.view.View>)
        fun showSinglePoster(uri: String, position: Int, imageView: ImageView, cvFrame: CardView)
        fun showCurrentNumOfPosters(total: String)
        fun showBlurBackground(image: Bitmap)
    }
}