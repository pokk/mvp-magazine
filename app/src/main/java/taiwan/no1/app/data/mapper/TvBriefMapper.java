package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieBriefModel;
import taiwan.no1.app.mvp.models.TvBriefModel;

/**
 * Mapper class used to transform between {@link MovieBriefModel} (in the kotlin layer) and
 * {@link MovieBriefEntity} (in the data layer).
 *
 * @author Jieyi
 * @since 12/28/16
 */

@Singleton
public class TvBriefMapper implements IBeanMapper<TvBriefModel, TvBriefEntity> {
    @Inject
    public TvBriefMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TvBriefEntity transformFrom(@NonNull TvBriefModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvBriefModel transformTo(@NonNull TvBriefEntity entity) {
        // We may not use all of information, then we will remove some redundant information.
        return new TvBriefModel(entity.getPoster_path(),
                                entity.getPopularity(),
                                entity.getId(),
                                entity.getBackdrop_path(),
                                entity.getVote_average(),
                                entity.getOverview(),
                                entity.getFirst_air_date(),
                                entity.getOriginal_language(),
                                entity.getVote_count(),
                                entity.getName(),
                                entity.getOriginal_name(),
                                new ArrayList<>(entity.getOrigin_country()),
                                new ArrayList<>(entity.getGenre_ids()));
    }
}
