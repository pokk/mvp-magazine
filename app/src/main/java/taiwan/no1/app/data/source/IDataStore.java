package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.CastListResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;
import taiwan.no1.app.data.entities.TvListResEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author Jieyi
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieListResEntity}.
     */
    @Nullable
    Observable<MovieListResEntity> moviesEntities(final CloudDataStore.Movies category, final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieListResEntity}.
     */
    @Nullable
    Observable<MovieListWithDateResEntity> moviesWithDateEntities(final CloudDataStore.Movies category, final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieDetailEntity}.
     */
    @Nullable
    Observable<MovieDetailEntity> movieDetailEntities(final int id);

    /**
     * Get an {@link rx.Observable} which will emit a {@link CastDetailEntity}.
     */
    @Nullable
    Observable<CastDetailEntity> castDetailEntities(final int id);

    /**
     * Get an {@link rx.Observable} which will emit a {@link TvListResEntity}.
     */
    @Nullable
    Observable<TvListResEntity> TvsEntities(final CloudDataStore.Tvs category, final int page);

    @Nullable
    Observable<TVDetailEntity> tvDetailEntities(final int id);

    /**
     * Get an {@link rx.Observable} which will emit a {@link CastListResEntity}.
     */
    @Nullable
    Observable<CastListResEntity> popularCastEntities(final int page);
}
