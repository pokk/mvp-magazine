package taiwan.no1.app.ui.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.intrusoft.squint.DiagonalView
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.mvp.models.ImageInfoModel

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */

class DropMoviePagerAdapter(val context: Context, val dropPosterList: List<ImageInfoModel>): PagerAdapter() {
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = this.dropPosterList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val dvDropPoster: DiagonalView = this.layoutInflater.inflate(R.layout.item_mvoie_poster,
                null,
                false) as DiagonalView

        // TODO: 1/12/17 Take the listener away as a new object. 
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASE_IMAGE_URL + this.dropPosterList[position].file_path).
                fitCenter().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                listener(object: RequestListener<String, GlideDrawable> {
                    override fun onException(e: Exception,
                                             model: String,
                                             target: Target<GlideDrawable>,
                                             isFirstResource: Boolean): Boolean = false

                    override fun onResourceReady(resource: GlideDrawable,
                                                 model: String,
                                                 target: Target<GlideDrawable>,
                                                 isFromMemoryCache: Boolean,
                                                 isFirstResource: Boolean): Boolean = let {
                        dvDropPoster.solidColor = Color.TRANSPARENT
                        false
                    }
                }).
                into(dvDropPoster)
        container.addView(dvDropPoster)

        return dvDropPoster
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}