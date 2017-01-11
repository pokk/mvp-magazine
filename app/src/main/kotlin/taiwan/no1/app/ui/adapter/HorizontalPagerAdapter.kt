package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
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
    private val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun getCount(): Int = if (isTwoWay) 6 else this.imageLists.size

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = mLayoutInflater.inflate(R.layout.item_gallery, container, false)
        //        if (isTwoWay) {
        //            view = mLayoutInflater.inflate(R.layout.two_way_item, container, false);
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

        val ivPoster: ImageView = view.findViewById(R.id.img_item) as ImageView
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

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
}
