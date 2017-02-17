package taiwan.no1.app.ui.adapter.viewholder

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.view.View
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.customize.AdjustHeightImageView
import taiwan.no1.app.ui.fragments.CastDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG
import taiwan.no1.app.utilies.ViewUtils

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class CastListViewHolder(val view: View): BaseViewHolder(view) {
    private val item by bindView<CardView>(R.id.item_cast_brief)
    private val ivPoster by bindView<AdjustHeightImageView>(R.id.iv_cast_poster)
    private val tvName by bindView<TextView>(R.id.tv_name)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        // Cast the model data type to MovieBriefModel.
        (model as CastBriefModel).let {
            ViewUtils.loadBitmapToView(this.mContext.applicationContext,
                    TMDBConfig.BASE_IMAGE_URL + it.profile_path,
                    listener = object: BitmapImageViewTarget(this.ivPoster) {
                        override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                            // Extract the color from pic.
                            Palette.from(resource).generate().let {
                                it.getDarkVibrantColor(Color.argb(153, 79, 79, 79)).let {
                                    // Set the fog in front of the backdrop.
                                    tvName.setBackgroundColor(Color.argb(153,
                                            Color.red(it),
                                            Color.green(it),
                                            Color.blue(it)))
                                }
                            }
                            ivPoster.heightRatio = resource.height / resource.width.toFloat()
                            super.onResourceReady(resource, glideAnimation)
                        }
                    })
            this.tvName.text = it.name
            this.item.setOnClickListener {
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(NAVIGATOR_ARG_FRAGMENT,
                                CastDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
                        Pair(NAVIGATOR_ARG_TAG, adapter.fragmentTag),
                        Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_SHARED_ELEMENTS,
                                hashMapOf(Pair(ivPoster, ivPoster.transitionName)))))
            }
        }
    }
}