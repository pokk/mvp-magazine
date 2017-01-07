package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDatesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieDatesModel;

/**
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class MovieDatesMapper implements IBeanMapper<MovieDatesModel, MovieDatesEntity> {

    @Inject
    public MovieDatesMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieDatesEntity transformFrom(@NonNull MovieDatesModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieDatesModel transformTo(@NonNull MovieDatesEntity entity) {
        return new MovieDatesModel(entity.getMaximum(), entity.getMinimum());
    }
}
