package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.ImageProfileEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.ImageProfileModel;

/**
 * Mapper class used to transform between {@link ImageProfileModel} (in the kotlin layer) and {@link ImageProfileEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class ImageProfileMapper implements IBeanMapper<ImageProfileModel, ImageProfileEntity> {
    @Inject
    public ImageProfileMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public ImageProfileEntity transformFrom(@NonNull ImageProfileModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public ImageProfileModel transformTo(@NonNull ImageProfileEntity entity) {
        return new ImageProfileModel(entity.getAspect_ratio(),
                                     entity.getFile_path(),
                                     entity.getHeight(),
                                     entity.getIso_639_1(),
                                     entity.getVote_average(),
                                     entity.getVote_count(),
                                     entity.getWidth());
    }
}
