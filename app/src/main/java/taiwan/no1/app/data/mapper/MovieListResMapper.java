package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieListResModel;

/**
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class MovieListResMapper implements IBeanMapper<MovieListResModel, MovieListResEntity> {
    @Inject MovieBriefMapper movieBriefMapper;

    @Inject
    public MovieListResMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieListResEntity transformFrom(@NonNull MovieListResModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieListResModel transformTo(@NonNull MovieListResEntity entity) {
        List<MovieBriefModel> movieBriefModel = Queryable.from(entity.getMovieEntities())
                                                         .map(this.movieBriefMapper::transformTo)
                                                         .toList();

        return new MovieListResModel(entity.getPage(),
                                     entity.getTotal_results(),
                                     entity.getTotal_pages(),
                                     movieBriefModel);
    }
}
