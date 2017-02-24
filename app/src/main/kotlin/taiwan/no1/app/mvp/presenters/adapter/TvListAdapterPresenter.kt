package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.constant.Constant
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract.View
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.ui.fragments.TvDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class TvListAdapterPresenter: BaseAdapterPresenter<View, TvBriefModel>(), Presenter {
    override fun init(viewHolder: View, model: TvBriefModel) {
        super.init(viewHolder, model)
        val genresString: StringBuilder = StringBuilder()
        val maxLengthOfGenres: Int = this.model.genre_ids?.let {
            if (it.size >= 3) 3 else it.size
        } ?: 0

        this.viewHolder.showTvPoster(TMDBConfig.BASE_IMAGE_URL + this.model.poster_path)
        this.viewHolder.showTvBackDrop(TMDBConfig.BASE_IMAGE_URL + this.model.backdrop_path)
        this.viewHolder.showTvTitle(this.model.name.orEmpty())
        this.viewHolder.showTvReleaseDate(this.model.first_air_date.orEmpty())

        (0..maxLengthOfGenres - 1).forEach { i ->
            Constant.Genres.values().forEach {
                if (this.model.genre_ids!![i] == it.id)
                    genresString.append("${it.categoryName} | ")
            }
        }
        this.viewHolder.showTvGenres(genresString.toString().dropLast(3))
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_FRAGMENT,
                        TvDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(ViewPagerMainCtrlFragment.NAVIGATOR_ARG_TAG, tag)))
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