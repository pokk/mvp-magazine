package taiwan.no1.accounting.domain;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.accounting.data.AccountRepository;
import taiwan.no1.accounting.domain.executor.PostExecutionThread;
import taiwan.no1.accounting.domain.executor.ThreadExecutor;
import taiwan.no1.accounting.mvp.models.FakeModel;


/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 9/6/16
 */
public class CreateFakeCase extends BaseCase<CreateFakeCase.Requests> {
    private final AccountRepository accountRepository;

    @Inject
    CreateFakeCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AccountRepository accountRepository) {
        super(threadExecutor, postExecutionThread);

        this.accountRepository = accountRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return accountRepository.CreateFakes(this.requestValues.fakeModel);
    }

    public static final class Requests implements BaseCase.RequestValues {
        private final FakeModel fakeModel;

        public Requests(FakeModel fakeModel) {this.fakeModel = fakeModel;}
    }
}
