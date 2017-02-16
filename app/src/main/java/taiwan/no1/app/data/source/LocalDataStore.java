package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.ListResEntity;
import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.data.entities.cast.CastDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.data.entities.movie.MovieDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.search.SearchMovieEntity;
import taiwan.no1.app.data.entities.tv.TvBriefEntity;
import taiwan.no1.app.data.entities.tv.TvDetailEntity;

/**
 * @author Jieyi
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    @Deprecated
    public Observable<ListResEntity<MovieBriefEntity>> moviesEntities(CloudDataStore.Movies category, int page) {
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
    public Observable<ListResEntity<TvBriefEntity>> TvsEntities(CloudDataStore.Tvs category, int page) {
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
    public Observable<ListResEntity<CastBriefEntity>> popularCastEntities(int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<SearchMovieEntity> searchMovieEntities(String language,
                                                             String query,
                                                             int page,
                                                             boolean include_adult,
                                                             String region,
                                                             int year,
                                                             int primary_release_year) {
        throw new Error("No-op");
    }
}
