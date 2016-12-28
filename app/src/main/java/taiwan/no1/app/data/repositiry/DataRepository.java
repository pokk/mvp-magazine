package taiwan.no1.app.data.repositiry;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.data.mapper.MovieEntityMapper;
import taiwan.no1.app.data.source.factory.DataStoreFactory;
import taiwan.no1.app.domain.repository.IRepository;
import taiwan.no1.app.mvp.models.MovieModel;

/**
 * Low layer pure entity convert to kotlin layer data model from the repositories.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class DataRepository implements IRepository {
    private final DataStoreFactory dataStoreFactory;
    @Inject MovieEntityMapper moviesMapper;

    @Inject
    DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @NonNull
    @Override
    public Observable<List<MovieModel>> getPopularMovies(final int page) {
        return dataStoreFactory.createCloud()
                               .getPopularMovieEntities(page)
                               .map(entity -> this.moviesMapper.transformTo(entity.getMovieEntities()));
    }
}
