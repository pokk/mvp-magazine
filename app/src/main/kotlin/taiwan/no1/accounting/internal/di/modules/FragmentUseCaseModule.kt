package taiwan.no1.accounting.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.domain.CreateFakeUseCase
import taiwan.no1.accounting.domain.executor.PostExecutionThread
import taiwan.no1.accounting.domain.executor.ThreadExecutor
import taiwan.no1.accounting.domain.repository.IAccountRepository
import taiwan.no1.accounting.internal.di.annotations.PerFragment

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
                    accountRepository: IAccountRepository): CreateFakeUseCase
            = CreateFakeUseCase(threadExecutor, postExecutionThread, accountRepository)
}