package taiwan.no1.app.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.target.Target
import com.hwangjr.rxbus.RxBus
import com.intrusoft.squint.DiagonalView
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.ui.fragments.MovieGalleryFragment
import taiwan.no1.app.ui.fragments.MovieListFragment
import taiwan.no1.app.ui.listeners.GlideCustomRequestListener
import taiwan.no1.app.utilies.ViewUtils

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */

class DropMoviePagerAdapter(val context: Context,
                            val dropPosterList: List<ImageInfoModel>,
                            val posterList: List<ImageInfoModel>,
                            val argFromFragment: Int): PagerAdapter() {
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = this.dropPosterList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val dvDropPoster: DiagonalView = this.layoutInflater.inflate(R.layout.item_movie_poster,
                null,
                false) as DiagonalView

        ViewUtils.loadImageToView(this.context.applicationContext,
                MovieDBConfig.BASE_IMAGE_URL + this.dropPosterList[position].file_path,
                dvDropPoster, object: GlideCustomRequestListener() {
            override fun onResourceReady(resource: GlideDrawable,
                                         model: String,
                                         target: Target<GlideDrawable>,
                                         isFromMemoryCache: Boolean,
                                         isFirstResource: Boolean): Boolean {
                dvDropPoster.solidColor = Color.TRANSPARENT
                return false
            }
        })
        dvDropPoster.setOnClickListener {
            RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                    Pair(MovieListFragment.NAVIGATOR_ARG_FRAGMENT,
                            MovieGalleryFragment.newInstance(posterList)),
                    Pair(MovieListFragment.NAVIGATOR_ARG_TAG, argFromFragment)))
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