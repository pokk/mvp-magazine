package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastListResModel;

/**
 * Mapper class used to transform between {@link CastListResModel.KnownForBean} (in the kotlin layer) and
 * {@link CastListResEntity.KnownForBean} (in the data layer).
 *
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class CastKnownForMapper implements IBeanMapper<CastListResModel.KnownForBean, CastListResEntity.KnownForBean> {
    @Inject
    public CastKnownForMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CastListResEntity.KnownForBean transformFrom(@NonNull CastListResModel.KnownForBean model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastListResModel.KnownForBean transformTo(@NonNull CastListResEntity.KnownForBean entity) {
        return new CastListResModel.KnownForBean(entity.getPoster_path(),
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
                                                 null != entity.getGenre_ids() ? new ArrayList<>(entity.getGenre_ids()) : null,
                                                 null != entity.getOrigin_country() ?
                                                         new ArrayList<>(entity.getOrigin_country()) :
                                                         null);
    }
}
