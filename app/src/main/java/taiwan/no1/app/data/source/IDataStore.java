package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieCastsResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.PopularResEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 * 
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a {@link PopularResEntity}.
     */
    @Nullable
    Observable<PopularResEntity> popularMovieEntities(final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieDetailEntity}.
     */
    @Nullable
    Observable<MovieDetailEntity> movieDetailEntities(final int id);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieCastsResEntity}.
     */
    @Nullable
    Observable<MovieCastsResEntity> movieCastsEntities(final int id);

    /**
     * Get an {@link rx.Observable} which will emit a {@link CastDetailEntity}.
     */
    @Nullable
    Observable<CastDetailEntity> castDetailEntities(final int id);
}
