package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastBriefEntity;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.SearchListResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author Jieyi
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Retrieve a list of movie information by specific category, the response forms are difference between the below.
     *
     * @param category from {@link CloudDataStore.Movies}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link SearchListResEntity<MovieBriefEntity>}.
     */
    @Nullable
    Observable<SearchListResEntity<MovieBriefEntity>> moviesEntities(final CloudDataStore.Movies category,
                                                                     final int page);

    /**
     * Retrieve a list of movie information by specific category, the response forms are difference between the above.
     *
     * @param category from {@link CloudDataStore.Movies}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link MovieListWithDateResEntity}.
     */
    @Nullable
    Observable<MovieListWithDateResEntity> moviesWithDateEntities(final CloudDataStore.Movies category, final int page);

    /**
     * Retrieve a detail movie information by the movie id.
     *
     * @param id movie id.
     * @return an {@link rx.Observable} which will emit a {@link MovieDetailEntity}.
     */
    @Nullable
    Observable<MovieDetailEntity> movieDetailEntities(final int id);

    /**
     * Retrieve a detail cast information by the cast id.
     *
     * @param id cast id.
     * @return an {@link rx.Observable} which will emit a {@link CastDetailEntity}.
     */
    @Nullable
    Observable<CastDetailEntity> castDetailEntities(final int id);

    /**
     * Retrieve a list of tv information by specific category.
     *
     * @param category from {@link CloudDataStore.Tvs}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link SearchListResEntity<TvBriefEntity>}.
     */
    @Nullable
    Observable<SearchListResEntity<TvBriefEntity>> TvsEntities(final CloudDataStore.Tvs category, final int page);

    /**
     * Retrieve a detail tv information by the tv id.
     *
     * @param id tv id.
     * @return an {@link rx.Observable} which will emit a {@link TVDetailEntity}.
     */
    @Nullable
    Observable<TVDetailEntity> tvDetailEntities(final int id);

    /**
     * Retrieve a list of the popular cast information.
     *
     * @param page a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link SearchListResEntity<CastBriefEntity>}.
     */
    @Nullable
    Observable<SearchListResEntity<CastBriefEntity>> popularCastEntities(final int page);
}
