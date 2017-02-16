package taiwan.no1.app.domain.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import taiwan.no1.app.domain.executor.PostExecutionThread;
import taiwan.no1.app.domain.executor.ThreadExecutor;
import taiwan.no1.app.domain.repository.IRepository;

/**
 * Created by weian on 2017/2/16.
 */

public class SearchTvShows extends BaseUseCase<SearchTvShows.Requests> {

    public SearchTvShows(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IRepository repository) {
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
        return this.repository.searchTvShows(this.requestValues.language,
                this.requestValues.query,
                this.requestValues.page,
                this.requestValues.first_air_date_year);
    }

    public static final class Requests extends BaseUseCase.RequestValues {
        private final String language;
        private final String query;
        private final int page;
        private final int first_air_date_year;

        public Requests(String language, String query, int page,
                        int first_air_date_year) {
            this.language = language;
            this.query = query;
            this.page = page;
            this.first_air_date_year = first_air_date_year;
        }
    }
}
