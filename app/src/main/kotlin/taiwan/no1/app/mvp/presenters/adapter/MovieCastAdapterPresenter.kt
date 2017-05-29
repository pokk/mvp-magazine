package taiwan.no1.app.mvp.presenters.adapter

import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag.FRAGMENT_CHILD_NAVIGATOR
import taiwan.no1.app.api.config.TMDBConfig.BASE_IMAGE_URL
import taiwan.no1.app.mvp.contracts.adapter.MovieCastAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.MovieCastAdapterContract.View
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.ui.fragments.CastDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieCastAdapterPresenter: BaseAdapterPresenter<View, FilmCastsModel.CastBean>(), Presenter {
    override fun init(viewHolder: View, model: FilmCastsModel.CastBean) {
        super.init(viewHolder, model)

        this.viewHolder.showCastProfilePhoto(BASE_IMAGE_URL + this.model.profile_path)
        this.viewHolder.showCastInfo(this.model.character.orEmpty(), this.model.name.orEmpty())
    }

    override fun onItemClicked(tag: Int) {
        RxBus.get().post(FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                Pair(NAVIGATOR_ARG_FRAGMENT, CastDetailFragment.newInstance(model.id.toString(), tag)),
                Pair(NAVIGATOR_ARG_TAG, tag)))
    }
}