package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Color.LTGRAY
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.CastListAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.CastListAdapterContract.View
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.ui.fragments.CastDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class CastListAdapterPresenter: BaseAdapterPresenter<View, CastBriefModel>(), Presenter {
    override fun init(viewHolder: View, model: CastBriefModel) {
        super.init(viewHolder, model)

        this.viewHolder.showProfile(BASE_IMAGE_URL + this.model.profile_path)
        this.viewHolder.showTvName(this.model.name.orEmpty())
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, CastDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
//                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_SHARED_ELEMENTS,
//                        hashMapOf(Pair(ivPoster, ivPoster.transitionName)))))
    }

    override fun onResourceFinished(bitmap: Bitmap) {
        this.captureColor(bitmap) {
            it.getDarkVibrantColor(Color.argb(153, 79, 79, 79)).let {
                // Set the fog in front of the backdrop.
                this.viewHolder.showTvNameBgColor(Color.argb(153, Color.red(it), Color.green(it), Color.blue(it)))
            }
            this.viewHolder.showTvNameTextColor(it.darkVibrantSwatch?.titleTextColor ?: LTGRAY)
        }
        this.viewHolder.resetHeightRatio(bitmap.height / bitmap.width.toFloat())
    }
} 