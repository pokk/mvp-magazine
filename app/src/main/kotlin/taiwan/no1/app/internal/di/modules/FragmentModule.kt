package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.usecase.CastDetail
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.*
import taiwan.no1.app.mvp.presenters.*

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
    fun provideMoviePopularPresenter(movies: MovieLists): MoviePopularContract.Presenter =
            MoviePopularPresenter(movies)

    @Provides
    @PerFragment
    fun provideMovieTopRatedPresenter(movies: MovieLists): MovieTopRatedContract.Presenter =
            MovieTopRatedPresenter(movies)

    @Provides
    @PerFragment
    fun provideMovieNowPlayingPresenter(movies: MovieLists): MovieNowPlayingContract.Presenter =
            MovieNowPlayingPresenter(movies)

    @Provides
    @PerFragment
    fun provideMovieUpComingPresenter(movies: MovieLists): MovieUpComingContract.Presenter =
            MovieUpComingPresenter(movies)

    @Provides
    @PerFragment
    fun provideMovieDetailPresenter(movieDetail: MovieDetail): MovieDetailContract.Presenter =
            MovieDetailPresenter(movieDetail)

    @Provides
    @PerFragment
    fun provideMovieGalleryPresenter(): MovieGalleryContract.Presenter =
            MovieGalleryPresenter()

    @Provides
    @PerFragment
    fun provideCaseDetailPresenter(castDetail: CastDetail): CastDetailContract.Presenter =
            CastDetailPresenter(castDetail)
}