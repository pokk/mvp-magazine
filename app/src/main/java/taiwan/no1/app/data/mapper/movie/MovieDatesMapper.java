package taiwan.no1.app.data.mapper.movie;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.movie.MovieDatesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.movie.MovieDatesModel;

/**
 * Base mapper class used to transform between {@link MovieDatesModel} (in the kotlin layer) and {@link MovieDatesEntity}
 * (in the data layer).
 *
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class MovieDatesMapper implements IBeanMapper<MovieDatesModel, MovieDatesEntity> {
    @Inject
    public MovieDatesMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public MovieDatesEntity transformFrom(@NonNull MovieDatesModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public MovieDatesModel transformTo(@NonNull MovieDatesEntity entity) {
        return new MovieDatesModel(entity.getMaximum(), entity.getMinimum());
    }
}
