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
 * @author  Jieyi
 * @since   2016/12/20
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
    fun provideTvListPresenter(): TVListContract.Presenter =
            TVListPresenter()
}