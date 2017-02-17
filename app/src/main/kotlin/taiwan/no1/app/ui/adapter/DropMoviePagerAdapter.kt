package taiwan.no1.app.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.hwangjr.rxbus.RxBus
import com.intrusoft.squint.DiagonalView
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.models.ImageProfileModel
import taiwan.no1.app.ui.fragments.MovieGalleryFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_SHARED_ELEMENTS
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */

class DropMoviePagerAdapter(val context: Context,
                            val dropPosterList: List<ImageProfileModel>, // For showing in the ViewPager.
                            val posterList: List<ImageProfileModel>, // For throwing to the next fragment.
                            val iv: ImageView,
                            val argFromFragment: Int): PagerAdapter() {
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = this.dropPosterList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val dvDropPoster: DiagonalView = this.layoutInflater.inflate(R.layout.item_movie_poster,
                null,
                false) as DiagonalView

        ViewUtils.loadBitmapToView(this.context.applicationContext,
                TMDBConfig.BASE_IMAGE_URL + this.dropPosterList[position].file_path,
                dvDropPoster, object: BitmapImageViewTarget(dvDropPoster) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                dvDropPoster.solidColor = Color.TRANSPARENT
                super.onResourceReady(resource, glideAnimation)
            }
            //            override fun onResourceReady(resource: GlideDrawable,
//                                         model: String,
//                                         target: Target<GlideDrawable>,
//                                         isFromMemoryCache: Boolean,
//                                         isFirstResource: Boolean): Boolean {
//                dvDropPoster.solidColor = Color.TRANSPARENT
//                return false
//            }
        })
        dvDropPoster.setOnClickListener {
            if (posterList.isNotEmpty()) {
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(NAVIGATOR_ARG_FRAGMENT,
                                MovieGalleryFragment.newInstance(posterList)),
                        Pair(NAVIGATOR_ARG_TAG, argFromFragment),
                        Pair(NAVIGATOR_ARG_SHARED_ELEMENTS, hashMapOf(
                                Pair(iv, iv.transitionName)))))
            }
            else {
                Toast.makeText(this.context, "There are no movie poster photos. :(", Toast.LENGTH_SHORT).show()
            }
        }
        container.addView(dvDropPoster)

        return dvDropPoster
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (`object` as DiagonalView).apply {
            this.setOnClickListener(null)
            container.removeView(this)
        }
    }
}