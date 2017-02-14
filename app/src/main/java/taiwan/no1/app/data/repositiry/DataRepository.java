package taiwan.no1.app.data.repositiry;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.data.entities.tv.TvBriefEntity;
import taiwan.no1.app.data.mapper.FilmCastsMapper;
import taiwan.no1.app.data.mapper.cast.CastDetailMapper;
import taiwan.no1.app.data.mapper.cast.CastListResMapper;
import taiwan.no1.app.data.mapper.movie.MovieBriefMapper;
import taiwan.no1.app.data.mapper.movie.MovieDetailMapper;
import taiwan.no1.app.data.mapper.tv.TvBriefMapper;
import taiwan.no1.app.data.mapper.tv.TvDetailMapper;
import taiwan.no1.app.data.mapper.tv.TvListResMapper;
import taiwan.no1.app.data.source.CloudDataStore;
import taiwan.no1.app.data.source.IDataStore;
import taiwan.no1.app.data.source.factory.DataStoreFactory;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.cast.CastDetailModel;
import taiwan.no1.app.mvp.models.cast.CastListResModel;
import taiwan.no1.app.mvp.models.movie.MovieBriefModel;
import taiwan.no1.app.mvp.models.movie.MovieDetailModel;
import taiwan.no1.app.mvp.models.tv.TvBriefModel;
import taiwan.no1.app.mvp.models.tv.TvDetailModel;

/**
 * Low layer pure entity convert to kotlin layer data model from the repositories.
 *
 * @author Jieyi
 * @since 12/6/16
 */

public class DataRepository implements IRepository {
    private final DataStoreFactory dataStoreFactory;
    @Inject MovieBriefMapper moviesMapper;
    @Inject MovieDetailMapper movieDetailMapper;
    @Inject FilmCastsMapper movieCastsMapper;
    @Inject CastDetailMapper castDetailMapper;
    @Inject TvListResMapper tvListResMapper;
    @Inject TvBriefMapper tvBriefMapper;
    @Inject TvDetailMapper tvDetailMapper;
    @Inject CastListResMapper castListResMapper;

    @Inject
    DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @NonNull
    @Override
    public Observable<List<MovieBriefModel>> movies(final CloudDataStore.Movies category, final int page) {
        IDataStore store = this.dataStoreFactory.createCloud();
        switch (category) {
            case POPULAR:
            case TOP_RATED:
                return store.moviesEntities(category, page).map(entity -> transitionMovie(entity.getResults()));
            case NOW_PLAYING:
            case UP_COMING:
                return store.moviesWithDateEntities(category, page).map(entity -> transitionMovie(entity.getResults()));
        }

        throw new Error("Movies doesn't have this type!");
    }

    @NonNull
    private List<MovieBriefModel> transitionMovie(@NonNull final List<MovieBriefEntity> entities) {
        return Queryable.from(entities).map(this.moviesMapper::transformTo).toList();
    }

    @NonNull
    @Override
    public Observable<MovieDetailModel> detailMovie(final int id) {
        return this.dataStoreFactory.createCloud().movieDetailEntities(id).map(this.movieDetailMapper::transformTo);
    }

    @NonNull
    @Override
    public Observable<CastDetailModel> castDetail(final int id) {
        return this.dataStoreFactory.createCloud().castDetailEntities(id).map(this.castDetailMapper::transformTo);
    }

    @NonNull
    @Override
    public Observable<List<TvBriefModel>> tvs(CloudDataStore.Tvs category, int page) {
        return this.dataStoreFactory.createCloud()
                                    .TvsEntities(category, page)
                                    .map(entity -> transitionTv(entity.getResults()));
    }

    @NonNull
    private List<TvBriefModel> transitionTv(@NonNull final List<TvBriefEntity> entities) {
        return Queryable.from(entities).map(this.tvBriefMapper::transformTo).toList();
    }

    @NonNull
    @Override
    public Observable<TvDetailModel> detailTV(int id) {
        return this.dataStoreFactory.createCloud().tvDetailEntities(id).map(this.tvDetailMapper::transformTo);
    }

    @NonNull
    @Override
    public Observable<CastListResModel> casts(int id) {
        return this.dataStoreFactory.createCloud().popularCastEntities(id).map(this.castListResMapper::transformTo);
    }
}
