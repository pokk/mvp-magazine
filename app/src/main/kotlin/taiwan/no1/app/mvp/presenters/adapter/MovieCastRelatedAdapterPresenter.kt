package taiwan.no1.app.mvp.presenters.adapter

import android.graphics.Bitmap
import android.graphics.Color
import com.devrapid.kotlinknifer.getColorWithAlpha
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.MovieCastRelatedAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.MovieCastRelatedAdapterContract.View
import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.ui.fragments.MovieDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieCastRelatedAdapterPresenter: BaseAdapterPresenter<View, CreditsInFilmModel.CastInFilmBean>(), Presenter {
    override fun init(viewHolder: View, model: CreditsInFilmModel.CastInFilmBean) {
        super.init(viewHolder, model)

        this.viewHolder.showMoviePoster(BASE_IMAGE_URL + this.model.poster_path)
        this.viewHolder.showMovieReleaseDate(this.model.release_date.orEmpty())
        this.viewHolder.showMovieTitle(this.model.title.orEmpty())
    }

    override fun onResourceFinished(bitmap: Bitmap) {
        this.captureColor(bitmap, 24) {
            it.getDarkVibrantColor(Color.argb(0x55, 0x00, 0x00, 0x00)).let {
                this.viewHolder.setMovieTitleBg(getColorWithAlpha(it, 0.5f))
            }
        }
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, MovieDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
    }
} 