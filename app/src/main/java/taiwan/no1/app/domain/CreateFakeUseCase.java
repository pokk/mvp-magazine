package taiwan.no1.app.domain;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.FakeModel;


/**
 * This class is an implementation of {@link BaseUseCase} that represents a use case for an example.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 9/6/16
 */

public class CreateFakeUseCase extends BaseUseCase<CreateFakeUseCase.Requests> {
    private final IRepository repository;

    public CreateFakeUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             IRepository repository) {
        super(threadExecutor, postExecutionThread);

        this.repository = repository;
    }

    /**
     * Executes the current use case with request parameters.
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     */
    @Override
    public void execute(@NonNull final Requests request, @NonNull final Subscriber useCaseSubscriber) {
        this.requestValues = request;

        super.execute(request, useCaseSubscriber);
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link BaseUseCase}.
     *
     * @return {@link Observable} for connecting with a {@link Subscription} from the kotlin layer.
     */
    @NonNull
    @Override
    protected Observable buildUseCaseObservable() {
        return repository.CreateFakes(this.requestValues.fakeModel);
    }

    /**
     * Wrapping data requests for general situation.
     */
    public static final class Requests extends BaseUseCase.RequestValues {
        @NonNull private final FakeModel fakeModel;

        public Requests(@NonNull final FakeModel fakeModel) {this.fakeModel = fakeModel;}
    }
}
