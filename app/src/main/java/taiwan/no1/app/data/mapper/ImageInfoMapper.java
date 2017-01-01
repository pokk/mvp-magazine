package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.ImageInfoEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.ImageInfoModel;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */

@Singleton
public class ImageInfoMapper implements IBeanMapper<ImageInfoModel, ImageInfoEntity> {

    @Inject
    public ImageInfoMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public ImageInfoEntity transformFrom(@NonNull ImageInfoModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public ImageInfoModel transformTo(@NonNull ImageInfoEntity entity) {
        return new ImageInfoModel(entity.getAspect_ratio(),
                                  entity.getFile_path(),
                                  entity.getHeight(),
                                  entity.getIso_639_1(),
                                  entity.getVote_average(),
                                  entity.getVote_count(),
                                  entity.getWidth());
    }
}
