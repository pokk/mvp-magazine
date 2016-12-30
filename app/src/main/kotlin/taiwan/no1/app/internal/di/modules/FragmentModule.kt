package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.usecase.MovieCasts
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.domain.usecase.PopularMovies
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.presenters.MovieDetailPresenter
import taiwan.no1.app.mvp.presenters.MoviePopularPresenter

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

@Module
class FragmentModule {
    @Provides
    @PerFragment
    fun provideMoviePopularPresenter(movies: PopularMovies): MoviePopularContract.Presenter =
            MoviePopularPresenter(movies)

    @Provides
    @PerFragment
    fun provideMovieDetailPresenter(movieDetail: MovieDetail,
                                    movieCasts: MovieCasts): MovieDetailContract.Presenter =
            MovieDetailPresenter(movieDetail, movieCasts)
}