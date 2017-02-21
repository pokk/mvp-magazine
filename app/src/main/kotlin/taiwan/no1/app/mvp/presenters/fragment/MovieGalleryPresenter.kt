package taiwan.no1.app.mvp.presenters.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.fragment.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.ui.adapter.HorizontalPagerAdapter
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */
class MovieGalleryPresenter: BasePresenter<MovieGalleryContract.View>(), MovieGalleryContract.Presenter {
    private var imageSwitcherRatio: Double = 1.0
    private var oldAdapterItemIndex: Int = 0
    private var moviePostersInfo: List<ImageProfileModel> = emptyList()
    private var isFirstImageFinished: Boolean = false

    //region Presenter implementation
    override fun init(view: MovieGalleryContract.View) {
        super.init(view)
    }

    override fun updateISAspectRatio(ratio: Double) {
        this.imageSwitcherRatio = ratio
    }

    override fun updateIsFirstImg(isFinished: Boolean) {
        this.isFirstImageFinished = isFinished
    }

    override fun updateOldItemIndex(oldIndex: Int) {
        this.oldAdapterItemIndex = oldIndex
    }

    override fun updatePosters(moviePosters: List<ImageProfileModel>) {
        // TODO: 2/12/17 Need to do deep-copy.
        this.moviePostersInfo = moviePosters
        this.view.showPosters(this.moviePostersInfo)
    }

    override fun updatePageOfNumber(currentNum: Int) {
        this.view.showCurrentNumOfPosters("${currentNum + 1} / ${this.moviePostersInfo.size}")
    }

    override fun resizeImageToFitBackground(image: Bitmap) {
        this.view.showBlurBackground(ViewUtils.resizeImageAsRatio(this.imageSwitcherRatio, image))
    }

    override fun attachBackgroundFrom(hicvp: HorizontalInfiniteCycleViewPager) {
        if (this.isFirstImageFinished) {
            val presentItem: Bitmap? = this.extractBitmap(hicvp, hicvp.realItem)

            if (null == presentItem) {
                // TODO: 2/13/17 Here we may use a subscript.
                // FIXED: 2/10/17 Fixed way as below... Scroll -> attachBgd(Presenter) -> [if (img is null)] ->
                // FIXED: Notify(PagerAdapter) -> Finished loading -> attachBgd(Presenter) again.
                // Notify to the adapter.
                (hicvp.adapter as HorizontalPagerAdapter).notifyNotFinishLoadingYet = true
            }
            else {
                this.updatePageOfNumber(hicvp.realItem)
                this.resizeImageToFitBackground(Bitmap.createBitmap(presentItem))
                this.updateOldItemIndex(hicvp.currentItem)
            }
        }
    }

    // FIXED: 2/10/17 Changed the Glide listener to BitmapImageViewTarget then we can check the drawable type as well.
    override fun extractBitmap(hicvp: HorizontalInfiniteCycleViewPager, index: Int): Bitmap? =
            ((this.findViewPagerItem(hicvp, index)?.
                    findViewById(R.id.img_item) as ImageView).drawable as? BitmapDrawable)?.bitmap
    //endregion

    /**
     * Search the specific [View] item from the [HorizontalInfiniteCycleViewPager] by view pager's index.
     *
     * @param hicvp [HorizontalInfiniteCycleViewPager]
     * @param index index.
     * @return [View]
     */
    private fun findViewPagerItem(hicvp: HorizontalInfiniteCycleViewPager, index: Int): View? {
        (0..hicvp.childCount - 1).forEach {
            if (index == hicvp.getChildAt(it).tag)
                return hicvp.getChildAt(it)
        }

        return null
    }
}
