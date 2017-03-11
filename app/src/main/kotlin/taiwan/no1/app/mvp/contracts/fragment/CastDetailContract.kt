package taiwan.no1.app.mvp.contracts.fragment

import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   1/1/17
 */

interface CastDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestCastDetail(castId: Int)
        fun enterToGallery(fromFragment: Int)
    }

    interface View: IView, IFragmentView {
        fun showCastPoster(posterUri: String)
        fun showCastProfilePic(proPicUri: String)
        fun showCastBase(gender: String, name: String)
        fun showCastDetail(bio: String, birthday: String, bron: String, homepage: String, deathday: String)
        fun showRelatedMovie(casts: List<CreditsInFilmModel.CastInFilmBean>)
    }
}