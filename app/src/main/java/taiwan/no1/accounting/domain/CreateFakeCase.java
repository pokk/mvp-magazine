package taiwan.no1.accounting.domain;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.accounting.data.AccountRepository;
import taiwan.no1.accounting.domain.executor.PostExecutionThread;
import taiwan.no1.accounting.domain.executor.ThreadExecutor;

/**
 * Created by john on 9/6/16.
 */
public class CreateFakeCase extends BaseCase {
    private final AccountRepository accountRepository;

    @Inject
    CreateFakeCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);

        this.accountRepository = accountRepository;
    }

    public void execute(Subscriber UseCaseSubscriber) {
        super.execute(UseCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
