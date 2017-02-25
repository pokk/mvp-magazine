package taiwan.no1.app.mvp.presenters.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.contracts.fragment.MovieGalleryContract
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */
class MovieGalleryPresenter: BasePresenter<MovieGalleryContract.View>(), MovieGalleryContract.Presenter {
    private var oldAdapterItemIndex: Int = 0
    private var postersInfo: List<ImageProfileModel> = emptyList()
    private var isFirstImageFinished: Boolean = false
    // Checking the first photo is finished loading flag.
    private var isFirstLoaded: Boolean = false
    // The flag of that after scrolling, checking the present view's image is finished loading.
    private var notifyNotFinishLoadingYet: Boolean = false

    //region Presenter implementation
    override fun init(view: MovieGalleryContract.View) {
        super.init(view)
    }

    override fun updateOldItemIndex(oldIndex: Int) {
        this.oldAdapterItemIndex = oldIndex
    }

    override fun updatePosters(posters: List<ImageProfileModel>) {
        // TODO: 2/12/17 Need to do deep-copy.
        this.postersInfo = posters
        this.view.showPosters(this.createViewPagerViews(posters))
    }

    override fun updatePageOfNumber(currentNum: Int) {
        this.view.showCurrentNumOfPosters("${currentNum + 1} / ${this.postersInfo.size}")
    }

    override fun onResourceFinished(hicvp: HorizontalInfiniteCycleViewPager, isRatio: Double, position: Int) {
        // FIXED: 2/10/17 It won't crash after switched to other photos.
        if (this.notifyNotFinishLoadingYet) {
            // When scrolling to the photo isn't finished. And after the photo is finished loading.
            this.attachBackgroundFrom(hicvp, isRatio)
        }
        // Notify once when entry this view.
        if (0 == position && !this.isFirstLoaded) {
            // Entry the gallery view after the first photo was finished loading, doing this method to resize the photo size.
            this.isFirstImageFinished = true
            this.view.showBlurBackground(ViewUtils.resizeImageAsRatio(isRatio,
                    Bitmap.createBitmap(this.extractBitmap(hicvp, hicvp.realItem))))
            this.notifyNotFinishLoadingYet = false
            this.isFirstLoaded = true
        }
    }

    override fun attachBackgroundFrom(hicvp: HorizontalInfiniteCycleViewPager, isRatio: Double) {
        if (this.isFirstImageFinished) {
            val presentItem: Bitmap? = this.extractBitmap(hicvp, hicvp.realItem)

            // In anyway we have to update the page number every single sliding.
            this.updatePageOfNumber(hicvp.realItem)
            if (null == presentItem) {
                // FIXED: 2/10/17 Fixed way as below... Scroll -> attachBgd(Presenter) -> [if (img is null)] ->
                // FIXED: Notify(Presenter) -> Finished loading(View Listener) -> attachBgd(Presenter) again.
                // Notify to the presenter to set the flag.
                this.notifyNotFinishLoadingYet = true
            }
            else {
                this.view.showBlurBackground(ViewUtils.resizeImageAsRatio(isRatio,
                        Bitmap.createBitmap(this.extractBitmap(hicvp, hicvp.realItem))))
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

    /**
     * Creating a view list for view pager shows each of the view.
     *
     * @param posters posters' data.
     * @return a view of list.
     */
    private fun createViewPagerViews(posters: List<ImageProfileModel>): List<View> =
            posters.map {
                View.inflate(this.view.context(), R.layout.item_gallery, null)
            }.apply {
                this.forEachIndexed { i, view ->
                    val ivPoster: ImageView = view.findViewById(R.id.img_item) as ImageView
                    val cvFrame: CardView = view.findViewById(R.id.cv_frame) as CardView

                    // For finding this view easily.
                    view.tag = i
                    this@MovieGalleryPresenter.view.showSinglePoster(TMDBConfig.BASE_IMAGE_URL + posters[i].file_path,
                            i, ivPoster, cvFrame)
                }
            }
}
