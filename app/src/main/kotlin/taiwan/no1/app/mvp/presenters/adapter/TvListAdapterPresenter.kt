package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.constant.Constant
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.ui.fragments.TvDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class TvListAdapterPresenter: BaseAdapterPresenter<View, TvBriefModel>(), Presenter {
    override fun init(viewHolder: View, model: TvBriefModel) {
        super.init(viewHolder, model)

        this.viewHolder.showTvPoster(BASE_IMAGE_URL + this.model.poster_path)
        this.viewHolder.showTvBackDrop(BASE_IMAGE_URL + this.model.backdrop_path)
        this.viewHolder.showTvTitle(this.model.name.orEmpty())
        this.viewHolder.showTvReleaseDate(this.model.first_air_date.orEmpty())
        this.viewHolder.showTvGenres(this.model.genre_ids?.map { Constant.GenresMap[it].orEmpty() } ?: emptyList())
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, TvDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
    }

    override fun onBackdropResourceFinished(bitmap: Bitmap) {
        this.captureBbColor(bitmap)
    }

    override fun captureBbColor(bitmap: Bitmap) {
        // Extract the color from pic.
        Palette.from(bitmap).generate().getVibrantColor(Color.argb(99, 79, 79, 79)).let {
            // Set the fog in front of the backdrop.
            this.viewHolder.showTvBackdropFrog(Color.argb(33, Color.red(it), Color.green(it), Color.blue(it)))
        }
    }
} 