package taiwan.no1.app.data.repositiry;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.mapper.CastDetailMapper;
import taiwan.no1.app.data.mapper.MovieBriefMapper;
import taiwan.no1.app.data.mapper.MovieCastsMapper;
import taiwan.no1.app.data.mapper.MovieDetailMapper;
import taiwan.no1.app.data.mapper.TVDetailMapper;
import taiwan.no1.app.data.source.IDataStore;
import taiwan.no1.app.data.source.factory.DataStoreFactory;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.CastDetailModel;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;
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
    @Inject TVDetailMapper tvDetailMapper;

    public enum Movies {
        POPULAR,
        TOP_RATED,
        NOW_PLAYING,
        UP_COMING
    }

    @Inject
    DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @NonNull
    @Override
    public Observable<List<MovieBriefModel>> movies(final Movies category, final int page) {
        IDataStore store = this.dataStoreFactory.createCloud();
        switch (category) {
            case POPULAR:
                return store.popularMovieEntities(page).map(entity -> transition(entity.getMovieEntities()));
            case TOP_RATED:
                return store.topRatedMovieEntities(page).map(entity -> transition(entity.getMovieEntities()));
            case NOW_PLAYING:
                return store.nowPlayingMovieEntities(page)
                            .map(entity -> transition(entity.getMovieEntities()));
            case UP_COMING:
                return store.upComingMovieEntities(page).map(entity -> transition(entity.getMovieEntities()));
        }

        throw new Error("Movies doesn't have this type!");
    }

    @NonNull
    private List<MovieBriefModel> transition(@NonNull final List<MovieBriefEntity> entities) {
        return Queryable.from(entities).map(this.moviesMapper::transformTo).toList();
    }

    @NonNull
    @Override
    public Observable<MovieDetailModel> detailMovie(final int id) {
        return this.dataStoreFactory.createCloud()
                                    .movieDetailEntities(id)
                                    .map(entity -> this.movieDetailMapper.transformTo(entity));
    }

    @NonNull
    @Override
    public Observable<CastDetailModel> castDetail(final int id) {
        return this.dataStoreFactory.createCloud()
                                    .castDetailEntities(id)
                                    .map(entity -> this.castDetailMapper.transformTo(entity));
    }

    @NonNull
    @Override
    public Observable<TvDetailModel> detailTV(int id) {
        return this.dataStoreFactory.createCloud()
                                    .tvDetailEntities(id)
                                    .map(entity -> this.tvDetailMapper.transformTo(entity));
    }
}
