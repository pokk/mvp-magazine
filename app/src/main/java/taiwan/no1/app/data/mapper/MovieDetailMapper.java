package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieDetailModel;

/**
 * Mapper class used to transform between {@link MovieDetailModel} (in the kotlin layer) an
 * {@link MovieDetailEntity} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/29/16
 */

@Singleton
public class MovieDetailMapper implements IBeanMapper<MovieDetailModel, MovieDetailEntity> {

    @Inject
    public MovieDetailMapper() {
    }

    @NonNull
    @Override
    public MovieDetailEntity transformFrom(@NonNull MovieDetailModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieDetailModel transformTo(@NonNull MovieDetailEntity entity) {
        MovieDetailModel model = new MovieDetailModel();
        // TODO: 12/29/16 Make a MovieDetailModel builder and map.
        return model;
    }
}
