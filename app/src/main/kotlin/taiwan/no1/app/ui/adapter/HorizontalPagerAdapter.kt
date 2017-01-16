package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.target.Target
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.ui.listeners.GlideResizeRequestListener
import taiwan.no1.app.utilies.ViewUtils


/**
 * @author Jieyi
 * @since 11/1/17
 */

class HorizontalPagerAdapter(val context: Context,
                             val isTwoWay: Boolean,
                             val imageLists: List<ImageInfoModel>): PagerAdapter() {
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }
    private var isLoaded: Boolean = false

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = if (isTwoWay) 6 else this.imageLists.size

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = this.layoutInflater.inflate(R.layout.item_gallery, container, false)
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

        ViewUtils.loadImageToView(this.context.applicationContext,
                MovieDBConfig.BASE_IMAGE_URL + this.imageLists[position].file_path,
                ivPoster,
                object: GlideResizeRequestListener(cvFrame) {
                    override fun onResourceReady(resource: GlideDrawable,
                                                 model: String,
                                                 target: Target<GlideDrawable>,
                                                 isFromMemoryCache: Boolean,
                                                 isFirstResource: Boolean): Boolean {
                        // Notify once when entry this view. 
                        if (0 == position && !isLoaded) {
                            RxBus.get().post(RxbusTag.FRAGMENT_FINISH_LOADED, position.toString())
                            isLoaded = true
                        }
                        return super.onResourceReady(resource,
                                model,
                                target,
                                isFromMemoryCache,
                                isFirstResource)
                    }
                })

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
