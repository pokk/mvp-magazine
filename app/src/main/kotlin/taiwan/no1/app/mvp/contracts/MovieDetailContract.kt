package taiwan.no1.app.mvp.contracts

import taiwan.no1.app.mvp.models.ImageInfoModel
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieVideosModel
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
    }

    interface View: IView, IFragmentView {
        fun showMoviePosters(backdrops: List<ImageInfoModel>, posters: List<ImageInfoModel>)
        fun showMovieCover(posterUri: String)
        fun showMovieBase(movieTitle: String, releaseDate: String, runtime: String, score: Double)
        fun showMovieDetail(overview: String, status: String, languages: String, productions: String)
        fun showMovieCasts(casts: List<MovieCastsModel.CastBean>)
        fun showMovieCrews(crews: List<MovieCastsModel.CrewBean>)
        fun showRelatedMovies(relatedMovies: List<MovieBriefModel>)
        fun showMovieTrailers(trailers: List<MovieVideosModel>)
    }
}