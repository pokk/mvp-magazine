package taiwan.no1.app.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import taiwan.no1.app.mvp.models.FakeModel;
import taiwan.no1.app.mvp.models.MovieModel;

/**
 * Interface that represents a Repository for getting {@link FakeModel} related data.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 5/29/16
 */

public interface IRepository {
    /**
     * Get an {@link Observable} which will emit a {@link List<MovieModel>}.
     *
     * @param page a page of popular movies.
     * @return {@link Observable}
     */
    @NonNull
    Observable<List<MovieModel>> getPopularMovies(final int page);
}
