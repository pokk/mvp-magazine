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
        return this.repository.searchMovies(this.requestValues.language,
                this.requestValues.query,
                this.requestValues.page,
                this.requestValues.include_adult,
                this.requestValues.region,
                this.requestValues.year,
                this.requestValues.primary_release_year);
    }

    public static final class Requests extends BaseUseCase.RequestValues {
        private final String language;
        private final String query;
        private final int page;
        private final boolean include_adult;
        private final String region;
        private final int year;
        private final int primary_release_year;

        public Requests(String language, String query, int page,
                        boolean include_adult, String region, int year,
                        int primary_release_year) {
            this.language = language;
            this.query = query;
            this.page = page;
            this.include_adult = include_adult;
            this.region = region;
            this.year = year;
            this.primary_release_year = primary_release_year;
        }
    }
}
