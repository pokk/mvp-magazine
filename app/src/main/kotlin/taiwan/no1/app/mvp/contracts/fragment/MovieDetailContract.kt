package taiwan.no1.app.mvp.contracts.fragment

import com.intrusoft.squint.DiagonalView
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi
 * @since   12/29/16
 */

interface MovieDetailContract {
    interface Presenter: IPresenter<View> {
        fun requestMovieDetail(movieId: Int)
        fun onResourceFinished(view: android.view.View, tag: Int)
    }

    interface View: IView, IFragmentView {
        fun showMovieBackdrops(viewList: List<android.view.View>)
        fun showMovieSingleBackdrop(uri: String, diagonalView: DiagonalView)
        fun showMovieCover(posterUri: String)
        fun showMovieBase(movieTitle: String, releaseDate: String, runtime: String, score: Double)
        fun showMovieDetail(overview: String, status: String, languages: String, productions: String)
        fun showMovieCasts(casts: List<FilmCastsModel.CastBean>)
        fun showMovieCrews(crews: List<FilmCastsModel.CrewBean>)
        fun showRelatedMovies(relatedMovies: List<MovieBriefModel>)
        fun showMovieTrailers(trailers: List<FilmVideoModel>)
    }
}