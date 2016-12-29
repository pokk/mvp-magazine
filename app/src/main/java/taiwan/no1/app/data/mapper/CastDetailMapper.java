package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastDetailModel;

/**
 * Mapper class used to transform between {@link CastDetailModel} (in the kotlin layer) and
 * {@link CastDetailEntity} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/28/16
 */

@Singleton
public class CastDetailMapper implements IBeanMapper<CastDetailModel, CastDetailEntity> {

    @Inject
    public CastDetailMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public CastDetailEntity transformFrom(@NonNull CastDetailModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public CastDetailModel transformTo(@NonNull CastDetailEntity entity) {
        return new CastDetailModel(entity.isAdult(),
                                   entity.getBiography(),
                                   entity.getBirthday(),
                                   entity.getDeathday(),
                                   entity.getGender(),
                                   entity.getHomepage(),
                                   entity.getId(),
                                   entity.getImdb_id(),
                                   entity.getName(),
                                   entity.getPlace_of_birth(),
                                   entity.getPopularity(),
                                   entity.getProfile_path(),
                                   entity.getAlso_known_as());
    }
}
