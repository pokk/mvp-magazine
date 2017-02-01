package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;

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
    Observable<MovieListResEntity> popularMovieEntities(final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieListResEntity}.
     */
    @Nullable
    Observable<MovieListResEntity> topRatedMovieEntities(final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieListWithDateResEntity}.
     */
    @Nullable
    Observable<MovieListWithDateResEntity> nowPlayingMovieEntities(final int page);

    /**
     * Get an {@link rx.Observable} which will emit a {@link MovieListWithDateResEntity}.
     */
    @Nullable
    Observable<MovieListWithDateResEntity> upComingMovieEntities(final int page);

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

    @Nullable
    Observable<TVDetailEntity> tvDetailEntities(final int id);
}
