package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieDatesModel;
import taiwan.no1.app.mvp.models.MovieListWithDateResModel;

/**
 * @author Jieyi

 * @since 1/1/17
 */

@Singleton
public class MovieListWithDatesResMapper implements IBeanMapper<MovieListWithDateResModel, MovieListWithDateResEntity> {
    @Inject MovieBriefMapper movieBriefMapper;

    @Inject
    public MovieListWithDatesResMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieListWithDateResEntity transformFrom(@NonNull MovieListWithDateResModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieListWithDateResModel transformTo(@NonNull MovieListWithDateResEntity entity) {
        List<MovieBriefModel> movieBriefModel = Queryable.from(entity.getMovieEntities())
                                                         .map(this.movieBriefMapper::transformTo)
                                                         .toList();

        return new MovieListWithDateResModel(entity.getPage(),
                                             entity.getTotal_results(),
                                             entity.getTotal_pages(),
                                             null != entity.getDates() ?
                                                     new MovieDatesModel(entity.getDates().getMaximum(),
                                                                         entity.getDates().getMinimum()) :
                                                     null,
                                             movieBriefModel);
    }
}
