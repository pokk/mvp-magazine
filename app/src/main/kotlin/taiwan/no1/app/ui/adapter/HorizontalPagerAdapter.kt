package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.ImageInfoModel


/**
 * @author Jieyi
 * @since 11/1/17
 */

class HorizontalPagerAdapter(val context: Context,
                             val isTwoWay: Boolean,
                             val imageLists: List<ImageInfoModel>): PagerAdapter() {
    var itemHeight: Int = 0
    var itemWidth: Int = 0

    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = if (isTwoWay) 6 else this.imageLists.size

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

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

//        var newHeight: Int = 0
//        var newWidth: Int = 0
//        var rate: Float = 0f
//
//        AppLog.w(view.height, view.width, position)
//
//        if (this.imageLists[position].height > this.imageLists[position].width) {
//            newHeight = Math.min(cvFrame.layoutParams.height, this.imageLists[position].height)
//            rate = (this.imageLists[position].height / newHeight).toFloat()
//            newWidth = (this.imageLists[position].width * rate).toInt()
//        }
//        else {
//            newWidth = Math.min(cvFrame.layoutParams.width, this.imageLists[position].width)
//            rate = (this.imageLists[position].width / newWidth).toFloat()
//            newHeight = (this.imageLists[position].width * rate).toInt()
//        }
//        AppLog.w(newHeight, newWidth)
//        resizeImageView(cvFrame, newHeight, newWidth)

        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASE_IMAGE_URL + this.imageLists[position].file_path).
                fitCenter().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                into(ivPoster)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun resizeImageView(cardView: CardView, height: Int, width: Int) {
        val layoutParams = cardView.layoutParams
        layoutParams.height = height
        layoutParams.width = width
        cardView.layoutParams = layoutParams
    }
}
