package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CastBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastBriefModel;

/**
 * Mapper class used to transform between {@link CastBriefModel.KnownForBean} (in the kotlin layer) and
 * {@link CastBriefEntity.KnownForBean} (in the data layer).
 *
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class CastKnownForMapper implements IBeanMapper<CastBriefModel.KnownForBean, CastBriefEntity.KnownForBean> {
    @Inject
    public CastKnownForMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CastBriefEntity.KnownForBean transformFrom(@NonNull CastBriefModel.KnownForBean model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastBriefModel.KnownForBean transformTo(@NonNull CastBriefEntity.KnownForBean entity) {
        return new CastBriefModel.KnownForBean(entity.getPoster_path(),
                                               entity.isAdult(),
                                               entity.getOverview(),
                                               entity.getRelease_date(),
                                               entity.getOriginal_title(),
                                               entity.getId(),
                                               entity.getMedia_type(),
                                               entity.getOriginal_language(),
                                               entity.getTitle(),
                                               entity.getBackdrop_path(),
                                               entity.getPopularity(),
                                               entity.getVote_count(),
                                               entity.isVideo(),
                                               entity.getVote_average(),
                                               entity.getFirst_air_date(),
                                               entity.getName(),
                                               entity.getOriginal_name(),
                                               null != entity.getGenre_ids() ?
                                                       new ArrayList<>(entity.getGenre_ids()) :
                                                       null,
                                               null != entity.getOrigin_country() ?
                                                       new ArrayList<>(entity.getOrigin_country()) :
                                                       null);
    }
}
