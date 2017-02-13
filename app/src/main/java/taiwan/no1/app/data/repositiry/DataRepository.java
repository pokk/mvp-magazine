package taiwan.no1.app.data.repositiry;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;
import taiwan.no1.app.data.mapper.CastDetailMapper;
import taiwan.no1.app.data.mapper.CastListResMapper;
import taiwan.no1.app.data.mapper.MovieBriefMapper;
import taiwan.no1.app.data.mapper.MovieCastsMapper;
import taiwan.no1.app.data.mapper.MovieDetailMapper;
import taiwan.no1.app.data.mapper.TestDetailMapper;
import taiwan.no1.app.data.mapper.TvBriefMapper;
import taiwan.no1.app.data.mapper.TvListResMapper;
import taiwan.no1.app.data.source.CloudDataStore;
import taiwan.no1.app.data.source.IDataStore;
import taiwan.no1.app.data.source.factory.DataStoreFactory;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.CastDetailModel;
import taiwan.no1.app.mvp.models.CastListResModel;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.TvBriefModel;
import taiwan.no1.app.mvp.models.TvDetailModel;

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
    @Inject MovieCastsMapper movieCastsMapper;
    @Inject CastDetailMapper castDetailMapper;
    @Inject TvListResMapper tvListResMapper;
    @Inject TvBriefMapper tvBriefMapper;
    @Inject TestDetailMapper tvDetailMapper;
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
                return store.moviesEntities(category, page).map(entity -> transitionMovie(entity.getMovieEntities()));
            case NOW_PLAYING:
            case UP_COMING:
                return store.moviesWithDateEntities(category, page)
                            .map(entity -> transitionMovie(entity.getMovieEntities()));
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
