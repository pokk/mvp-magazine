package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;

/**
 * {@inheritDoc}
 *
 * @author weian
 * @since 2/1/17
 */

public class TvDetail extends BaseUseCase<TvDetail.Requests> {
    public TvDetail(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IRepository repository) {
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
        return this.repository.detailTv(this.requestValues.tvId);
    }

    public static final class Requests extends BaseUseCase.RequestValues {
        private final int tvId;

        public Requests(int tvId) {
            this.tvId = tvId;
        }
    }
}
