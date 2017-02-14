package taiwan.no1.app.data.mapper.movie;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.movie.MovieListWithDateResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.movie.MovieBriefModel;
import taiwan.no1.app.mvp.models.movie.MovieDatesModel;
import taiwan.no1.app.mvp.models.movie.MovieListWithDateResModel;

/**
 * Mapper class used to transform between {@link MovieListWithDateResModel} (in the kotlin layer) and {@link MovieListWithDateResEntity}
 * (in the data layer).
 *
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class MovieListWithDatesResMapper implements IBeanMapper<MovieListWithDateResModel, MovieListWithDateResEntity> {
    @Inject MovieBriefMapper movieBriefMapper;
    @Inject MovieDatesMapper movieDatesMapper;

    @Inject
    public MovieListWithDatesResMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public MovieListWithDateResEntity transformFrom(@NonNull MovieListWithDateResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public MovieListWithDateResModel transformTo(@NonNull MovieListWithDateResEntity entity) {
        List<MovieBriefModel> movieBriefModel = Queryable.from(entity.getResults())
                                                         .map(this.movieBriefMapper::transformTo)
                                                         .toList();
        MovieDatesModel movieDatesModel = this.movieDatesMapper.transformTo(entity.getDates());

        return new MovieListWithDateResModel(entity.getPage(),
                                             entity.getTotal_results(),
                                             entity.getTotal_pages(),
                                             null != entity.getDates() ? movieDatesModel : new MovieDatesModel("", ""),
                                             movieBriefModel);
    }
}
