package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.CastListResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;

/**
 * @author Jieyi
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    public Observable<MovieListResEntity> moviesEntities(CloudDataStore.Movies category, int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<MovieListWithDateResEntity> moviesWithDateEntities(CloudDataStore.Movies category, int page) {
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

    @Nullable
    @Override
    public Observable<TVDetailEntity> tvDetailEntities(final int id) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<CastListResEntity> popularCastEntities(int page) {
        throw new Error("No-op");
    }
}
