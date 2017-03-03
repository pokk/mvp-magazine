package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.adapter.*
import taiwan.no1.app.mvp.presenters.adapter.*
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import taiwan.no1.app.ui.adapter.viewholder.viewtype.ViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

@Module
class AdapterModule {
    @PerFragment
    @Provides
    fun provideViewTypeFactory(): IViewTypeFactory = ViewTypeFactory()

    @PerFragment
    @Provides
    fun provideCastListAdapterPresenter(): CastListAdapterContract.Presenter = CastListAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieCastAdapterPresenter(): MovieCastAdapterContract.Presenter = MovieCastAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieCastRelatedAdapterPresenter(): MovieCastRelatedAdapterContract.Presenter = MovieCastRelatedAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieCrewAdapterPresenter(): MovieCrewAdapterContract.Presenter = MovieCrewAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieListAdapterPresenter(): MovieListAdapterContract.Presenter = MovieListAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieRelatedAdapterPresenter(): MovieRelatedAdapterContract.Presenter = MovieRelatedAdapterPresenter()

    @PerFragment
    @Provides
    fun provideMovieTrailerAdapterPresenter(): TrailerAdapterContract.Presenter = MovieTrailerAdapterPresenter()

    @PerFragment
    @Provides
    fun provideTvListAdapterPresenter(): TvListAdapterContract.Presenter = TvListAdapterPresenter()

    @PerFragment
    @Provides
    fun provideTvSeasonAdapterPresenter(): TvSeasonAdapterContract.Presenter = TvSeasonAdapterPresenter()
}