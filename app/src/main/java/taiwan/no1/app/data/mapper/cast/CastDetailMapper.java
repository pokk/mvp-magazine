package taiwan.no1.app.data.mapper.cast;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.cast.CastDetailEntity;
import taiwan.no1.app.data.mapper.CreditsInFilmMapper;
import taiwan.no1.app.data.mapper.ImageProfileMapper;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CreditsInFilmModel;
import taiwan.no1.app.mvp.models.ImageProfileModel;
import taiwan.no1.app.mvp.models.cast.CastDetailModel;
import taiwan.no1.app.mvp.models.cast.CastImagesModel;

/**
 * Mapper class used to transform between {@link CastDetailModel} (in the kotlin layer) and {@link CastDetailEntity}
 * (in the data layer).
 *
 * @author Jieyi
 * @since 12/28/16
 */

@Singleton
public class CastDetailMapper implements IBeanMapper<CastDetailModel, CastDetailEntity> {
    @Inject ImageProfileMapper imageProfileMapper;
    @Inject CreditsInFilmMapper creditsInFilmMapper;

    @Inject
    public CastDetailMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CastDetailEntity transformFrom(@NonNull CastDetailModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastDetailModel transformTo(@NonNull CastDetailEntity entity) {
        List<ImageProfileModel> imageInfoModels = null != entity.getImages().getProfiles() ?
                Queryable.from(entity.getImages().getProfiles()).map(this.imageProfileMapper::transformTo).toList() :
                Collections.emptyList();
        CreditsInFilmModel creditsModels = this.creditsInFilmMapper.transformTo(entity.getCombined_credits());

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
                                   null != entity.getAlso_known_as() ?
                                           new ArrayList<>(entity.getAlso_known_as()) :
                                           Collections.emptyList(),
                                   new CastImagesModel(imageInfoModels),
                                   creditsModels);
    }
}
