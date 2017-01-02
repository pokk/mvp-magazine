package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.executor.PostExecutionThread
import taiwan.no1.app.domain.executor.ThreadExecutor
import taiwan.no1.app.domain.repository.IRepository
import taiwan.no1.app.domain.usecase.CastDetail
import taiwan.no1.app.domain.usecase.MovieDetail
import taiwan.no1.app.domain.usecase.MovieLists
import taiwan.no1.app.internal.di.annotations.PerFragment

/**
 * Dagger use case module that provides user related collaborators.
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/21/16
 */

@Module
class FragmentUseCaseModule {
    @Provides
    @PerFragment
    fun ProvideMovieDetail(threadExecutor: ThreadExecutor,
                           postExecutionThread: PostExecutionThread,
                           repository: IRepository): MovieDetail
            = MovieDetail(threadExecutor, postExecutionThread, repository)

    @Provides
    @PerFragment
    fun ProvideMovieLists(threadExecutor: ThreadExecutor,
                          postExecutionThread: PostExecutionThread,
                          repository: IRepository): MovieLists
            = MovieLists(threadExecutor, postExecutionThread, repository)

    @Provides
    @PerFragment
    fun ProvideCastDetail(threadExecutor: ThreadExecutor,
                          postExecutionThread: PostExecutionThread,
                          repository: IRepository): CastDetail
            = CastDetail(threadExecutor, postExecutionThread, repository)
}