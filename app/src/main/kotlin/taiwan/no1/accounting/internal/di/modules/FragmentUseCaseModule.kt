package taiwan.no1.accounting.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.domain.BaseUseCase
import taiwan.no1.accounting.domain.CreateFakeUseCase
import taiwan.no1.accounting.internal.di.annotations.PerFragment
import javax.inject.Named

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
    @Named("Fake")
    fun providesFake(createFakeUseCase: CreateFakeUseCase): BaseUseCase<CreateFakeUseCase.Requests> {
        return createFakeUseCase
    }

//    private fun CreateFakeUseCase(threadExecutor: ThreadExecutor,
//                                  postExecutionThread: PostExecutionThread,
//                                  accountRepository: IAccountRepository): BaseUseCase<CreateFakeUseCase.Requests> {
//        
//    }
}