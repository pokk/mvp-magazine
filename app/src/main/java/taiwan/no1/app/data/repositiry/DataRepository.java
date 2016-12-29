package taiwan.no1.app.data.repositiry;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.data.mapper.CastDetailMapper;
import taiwan.no1.app.data.mapper.MovieBriefMapper;
import taiwan.no1.app.data.mapper.MovieCastsMapper;
import taiwan.no1.app.data.mapper.MovieDetailMapper;
import taiwan.no1.app.data.source.factory.DataStoreFactory;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.CastDetailModel;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieCastsModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;

/**
 * Low layer pure entity convert to kotlin layer data model from the repositories.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class DataRepository implements IRepository {
    private final DataStoreFactory dataStoreFactory;
    @Inject MovieBriefMapper moviesMapper;
    @Inject MovieDetailMapper movieDetailMapper;
    @Inject MovieCastsMapper movieCastsMapper;
    @Inject CastDetailMapper castDetailMapper;

    @Inject
    DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @NonNull
    @Override
    public Observable<List<MovieBriefModel>> popularMovies(final int page) {
        return dataStoreFactory.createCloud().popularMovieEntities(page)
                               .map(entity -> this.moviesMapper.transformTo(entity.getMovieEntities()));
    }

    @NonNull
    @Override
    public Observable<MovieDetailModel> detailMovie(final int id) {
        return dataStoreFactory.createCloud()
                               .movieDetailEntities(id)
                               .map(entity -> this.movieDetailMapper.transformTo(entity));
    }

    @NonNull
    @Override
    public Observable<MovieCastsModel> movieCasts(final int id) {
        return dataStoreFactory.createCloud()
                               .movieCastsEntities(id)
                               .map(entity -> this.movieCastsMapper.transformTo(entity));
    }

    @NonNull
    @Override
    public Observable<CastDetailModel> castDetail(final int id) {
        return dataStoreFactory.createCloud()
                               .castDetailEntities(id)
                               .map(entity -> this.castDetailMapper.transformTo(entity));
    }
}
