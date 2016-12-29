package taiwan.no1.app.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;

/**
 * Interface that represents a Repository for getting a movie related data.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 5/29/16
 */

public interface IRepository {
    /**
     * Get an {@link Observable} which will emit a {@link List< MovieBriefModel >}.
     *
     * @param page a page of popular movies.
     * @return {@link Observable}
     */
    @NonNull
    Observable<List<MovieBriefModel>> popularMovies(final int page);

    /**
     * Get an {@link Observable} which will emit a .
     *
     * @param id a movie id.
     * @return {@link Observable}
     */
    @NonNull
    Observable<MovieDetailModel> detailMovie(final int id);
}
