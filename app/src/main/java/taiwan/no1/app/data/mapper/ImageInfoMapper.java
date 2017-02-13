package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.ImageProfileEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.ImageInfoModel;

/**
 * Mapper class used to transform between {@link ImageInfoModel} (in the kotlin layer) and {@link ImageProfileEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class ImageInfoMapper implements IBeanMapper<ImageInfoModel, ImageProfileEntity> {
    @Inject
    public ImageInfoMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public ImageProfileEntity transformFrom(@NonNull ImageInfoModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public ImageInfoModel transformTo(@NonNull ImageProfileEntity entity) {
        return new ImageInfoModel(entity.getAspect_ratio(),
                                  entity.getFile_path(),
                                  entity.getHeight(),
                                  entity.getIso_639_1(),
                                  entity.getVote_average(),
                                  entity.getVote_count(),
                                  entity.getWidth());
    }
}
