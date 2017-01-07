package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import taiwan.no1.app.data.repositiry.DataRepository;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;

/**
 * {@inheritDoc}
 *
 * @author Jieyi

 * @since 12/29/16
 */

public class MovieLists extends BaseUseCase<MovieLists.Requests> {
    public MovieLists(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                      IRepository repository) {
        super(threadExecutor, postExecutionThread, repository);
    }

    /**
     * {@inheritDoc}
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     */
    @Override
    public void execute(@NonNull final MovieLists.Requests request,
                        @NonNull final Subscriber useCaseSubscriber) {
        this.requestValues = request;

        super.execute(request, useCaseSubscriber);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link Observable} for connecting with a {@link Subscription} from the kotlin layer.
     */
    @NonNull
    @Override
    protected Observable buildUseCaseObservable() {
        return this.repository.movies(this.requestValues.category, this.requestValues.page);
    }

    /**
     * {@inheritDoc}
     */
    public static final class Requests extends BaseUseCase.RequestValues {
        private final DataRepository.Movies category;
        private final int page;

        public Requests(DataRepository.Movies category, int page) {
            this.category = category;
            this.page = page;
        }
    }
}
