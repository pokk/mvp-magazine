package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.MovieListResModel;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 1/1/17
 */

@Singleton
public class MovieListResMapper implements IBeanMapper<MovieListResModel, MovieListResEntity> {

    @Inject MovieBriefMapper mapper;

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
        List<MovieBriefModel> movieBriefModel = new ArrayList<>();
        for (MovieBriefEntity movieBriefEntity : entity.getMovieEntities()) {
            movieBriefModel.add(this.mapper.transformTo(movieBriefEntity));
        }

        return new MovieListResModel(entity.getPage(),
                                     entity.getTotal_results(),
                                     entity.getTotal_pages(),
                                     movieBriefModel);
    }
}
