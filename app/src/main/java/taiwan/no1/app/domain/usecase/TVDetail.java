package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;

/**
 * Created by weian on 2017/2/1.
 */

public class TVDetail extends BaseUseCase<TVDetail.Requests> {
    public TVDetail(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                    IRepository repository) {
        super(threadExecutor, postExecutionThread, repository);
    }

    @Override
    public void execute(@NonNull TVDetail.Requests request, @NonNull Subscriber useCaseSubscriber) {
        this.requestValues = request;
        super.execute(request, useCaseSubscriber);
    }

    @NonNull
    @Override
    protected Observable buildUseCaseObservable() {
        return this.repository.detailTV(this.requestValues.tvId);
    }

    public static final class Requests extends BaseUseCase.RequestValues {
        private final int tvId;

        public Requests(int tvId) {
            this.tvId = tvId;
        }
    }
}
