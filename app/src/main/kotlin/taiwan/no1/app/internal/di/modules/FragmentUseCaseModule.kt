package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.CreateFakeUseCase
import taiwan.no1.app.domain.executor.PostExecutionThread
import taiwan.no1.app.domain.executor.ThreadExecutor
import taiwan.no1.app.domain.repository.IRepository
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
    fun ProvideFake(threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread,
                    repository: IRepository): CreateFakeUseCase
            = CreateFakeUseCase(threadExecutor, postExecutionThread, repository)
}