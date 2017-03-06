package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.usecase.*
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.fragment.*
import taiwan.no1.app.mvp.presenters.fragment.*

/**
 *
 * @author  Jieyi
 * @since   12/20/16
 */

@Module
class FragmentModule {
    @Provides
    @PerFragment
    fun provideMovieListPresenter(movies: MovieLists): MovieListContract.Presenter =
            MovieListPresenter(movies)

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

    @Provides
    @PerFragment
    fun provideTvListPresenter(tvLists: TvLists): TvListContract.Presenter =
            TvListPresenter(tvLists)

    @Provides
    @PerFragment
    fun provideTvDetailPresenter(tvDetail: TvDetail): TvDetailContract.Presenter =
            TvDetailPresenter(tvDetail)

    @Provides
    @PerFragment
    fun provideTvSeasonPresenter(tvSeasonDetail: TvSeasonDetail): TvSeasonContract.Presenter =
            TvSeasonPresenter()

    @Provides
    @PerFragment
    fun provideTvEpisodePresenter(tvEpisodeDetail: TvEpisodeDetail): TvEpisodeContract.Presenter =
            TvEpisodePresenter()

    @Provides
    @PerFragment
    fun provideActressesMainPresenter(castCase: CastLists): ActressMainContract.Presenter =
            ActressMainPresenter(castCase)
}