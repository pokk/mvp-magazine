package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;

/**
 * Created by weian on 2017/2/15.
 */

public class SearchMovie extends BaseUseCase<SearchMovie.Requests> {
    public SearchMovie(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IRepository repository) {
        super(threadExecutor, postExecutionThread, repository);
    }

    @Override
    public void execute(@NonNull Requests request, @NonNull Subscriber useCaseSubscriber) {
        this.requestValues = request;
        super.execute(request, useCaseSubscriber);
    }

    @NonNull
    @Override
    protected Observable buildUseCaseObservable() {
        return this.repository;
    }

    public static final class Requests extends BaseUseCase.RequestValues {
        private final int api_key;

        public Requests(int api_key) {
            this.api_key = api_key;
        }
    }
}
