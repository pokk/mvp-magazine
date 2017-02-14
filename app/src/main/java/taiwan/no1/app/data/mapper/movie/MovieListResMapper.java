package taiwan.no1.app.data.mapper.movie;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.ListResEntity;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.movie.MovieBriefModel;
import taiwan.no1.app.mvp.models.movie.MovieListResModel;

/**
 * Mapper class used to transform between {@link MovieListResModel} (in the kotlin layer) an
 * {@link ListResEntity <MovieBriefEntity>} (in the data layer).
 *
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class MovieListResMapper implements IBeanMapper<MovieListResModel, ListResEntity<MovieBriefEntity>> {
    @Inject MovieBriefMapper movieBriefMapper;

    @Inject
    public MovieListResMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public ListResEntity<MovieBriefEntity> transformFrom(@NonNull MovieListResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public MovieListResModel transformTo(@NonNull ListResEntity<MovieBriefEntity> entity) {
        List<MovieBriefModel> movieBriefModel = Queryable.from(entity.getResults())
                                                         .map(this.movieBriefMapper::transformTo)
                                                         .toList();

        return new MovieListResModel(entity.getPage(),
                                     entity.getTotal_results(),
                                     entity.getTotal_pages(),
                                     movieBriefModel);
    }
}
