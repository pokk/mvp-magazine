package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastBriefEntity;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.SearchListResEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;
import taiwan.no1.app.data.entities.TvDetailEntity;

/**
 * @author Jieyi
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    @Deprecated
    public Observable<SearchListResEntity<MovieBriefEntity>> moviesEntities(CloudDataStore.Movies category, int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
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
    @Deprecated
    public Observable<SearchListResEntity<TvBriefEntity>> TvsEntities(CloudDataStore.Tvs category, int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<TvDetailEntity> tvDetailEntities(final int id) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    @Deprecated
    public Observable<SearchListResEntity<CastBriefEntity>> popularCastEntities(int page) {
        throw new Error("No-op");
    }
}
