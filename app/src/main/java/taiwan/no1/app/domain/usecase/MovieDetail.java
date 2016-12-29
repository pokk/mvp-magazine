package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;


/**
 * This class is an implementation of {@link BaseUseCase} that represents a use case for an example.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 9/6/16
 */

public class MovieDetail extends BaseUseCase<MovieDetail.Requests> {
    public MovieDetail(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                       IRepository repository) {
        super(threadExecutor, postExecutionThread, repository);
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
        return this.repository.detailMovie(this.requestValues.movieId);
    }

    /**
     * Wrapping data requests for general situation.
     */
    public static final class Requests extends BaseUseCase.RequestValues {
        private final int movieId;

        public Requests(int movieId) {this.movieId = movieId;}
    }
}
