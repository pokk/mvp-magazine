package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.usecase.CastDetail
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.CastDetailContract
import taiwan.no1.app.mvp.contracts.MovieDetailContract
import taiwan.no1.app.mvp.contracts.MovieGalleryContract
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.presenters.CastDetailPresenter
import taiwan.no1.app.mvp.presenters.MovieDetailPresenter
import taiwan.no1.app.mvp.presenters.MovieGalleryPresenter
import taiwan.no1.app.mvp.presenters.MoviePopularPresenter

/**
 *
 * @author  Jieyi
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