package taiwan.no1.accounting.domain;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.accounting.domain.executor.PostExecutionThread;
import taiwan.no1.accounting.domain.executor.ThreadExecutor;
import taiwan.no1.accounting.domain.repository.AccountRepository;
import taiwan.no1.accounting.mvp.models.FakeModel;


/**
 * This class is an implementation of {@link BaseUseCase} that represents a use case for an example.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 9/6/16
 */
public class CreateFakeUseCase extends BaseUseCase<CreateFakeUseCase.Requests> {
    private final AccountRepository accountRepository;

    @Inject
    CreateFakeUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);

        this.accountRepository = accountRepository;
    }

    /**
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     * @deprecated This method won't do anything, because this {@link Observable} need some parameters. The other same
     * method with parameters that should be used.
     */
    @Override
    @Deprecated
    public void execute(Subscriber useCaseSubscriber) {
        if (null != requestValues) {
            super.execute(useCaseSubscriber);
        }
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return accountRepository.CreateFakes(this.requestValues.fakeModel);
    }

    /**
     * Wrapping data requests for general situation.
     */
    public static final class Requests implements BaseUseCase.RequestValues {
        private final FakeModel fakeModel;

        public Requests(FakeModel fakeModel) {this.fakeModel = fakeModel;}
    }
}
