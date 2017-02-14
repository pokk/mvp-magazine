package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.FilmVideosEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.FilmVideoModel;

/**
 * Base mapper class used to transform between {@link FilmVideoModel} (in the kotlin layer) and {@link FilmVideosEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class FilmVideosMapper implements IBeanMapper<FilmVideoModel, FilmVideosEntity> {
    @Inject
    public FilmVideosMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public FilmVideosEntity transformFrom(@NonNull FilmVideoModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public FilmVideoModel transformTo(@NonNull FilmVideosEntity entity) {
        return new FilmVideoModel(entity.getId(),
                                  entity.getIso_639_1(),
                                  entity.getIso_3166_1(),
                                  entity.getKey(),
                                  entity.getName(),
                                  entity.getSite(),
                                  entity.getSize(),
                                  entity.getType());
    }
}
