package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;

/**
 * @author Jieyi
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    @Deprecated
    public Observable<MovieListResEntity> popularMovieEntities(final int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<MovieListResEntity> topRatedMovieEntities(int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<MovieListWithDateResEntity> nowPlayingMovieEntities(int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<MovieListWithDateResEntity> upComingMovieEntities(int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<MovieDetailEntity> movieDetailEntities(final int id) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<CastDetailEntity> castDetailEntities(final int id) {
        throw new Error("No-op");
    }
}
