package taiwan.no1.app.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.animation.GlideAnimation
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ViewUtils


/**
 * @author Jieyi
 * @since 11/1/17
 */

class HorizontalPagerAdapter(val context: Context,
                             val isTwoWay: Boolean,
                             val imageLists: List<ImageProfileModel>): PagerAdapter() {
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }
    // Checking the first photo is finished loading flag.
    private var isFirstLoaded: Boolean = false
    // The flag of that after scrolling, checking the present view's image is finished loading.
    var notifyNotFinishLoadingYet: Boolean = false
        set(value) {
            field = value
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = if (isTwoWay) 6 else this.imageLists.size

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = this.layoutInflater.inflate(R.layout.item_gallery, container, false).apply {
            // For easy to find this view.
            this.tag = position
        }
        val ivPoster: ImageView = view.findViewById(R.id.img_item) as ImageView
        val cvFrame: CardView = view.findViewById(R.id.cv_frame) as CardView
        //        if (isTwoWay) {
        //            view = layoutInflater.inflate(R.layout.two_way_item, container, false);
        //
        //            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
        //                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        //            verticalInfiniteCycleViewPager.setAdapter(
        //                    new VerticalPagerAdapter(mContext)
        //            );
        //            verticalInfiniteCycleViewPager.setCurrentItem(position);
        //        } else {
        //            setupItem(view, LIBRARIES[position]);
        //        }

        ViewUtils.loadBitmapToView(this.context.applicationContext,
                TMDBConfig.BASE_IMAGE_URL + this.imageLists[position].file_path,
                listener = object: GlideResizeTargetListener(ivPoster, cvFrame) {
                    override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                        super.onResourceReady(resource, glideAnimation)
                        // FIXED: 2/10/17 It won't crash after switched to other photos.
                        if (notifyNotFinishLoadingYet) {
                            RxBus.get().post(RxbusTag.FRAGMENT_FINISHED_LOADING_IMG, position.toString())
                            notifyNotFinishLoadingYet = false
                        }
                        // Notify once when entry this view.
                        if (0 == position && !isFirstLoaded) {
                            RxBus.get().post(RxbusTag.FRAGMENT_FINISHED_FIRST_IMG, position.toString())
                            isFirstLoaded = true
                        }
                    }
                }
        )

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
