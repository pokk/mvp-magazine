package taiwan.no1.app.data.mapper.cast;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.cast.CastBriefModel;

/**
 * Base mapper class used to transform between {@link CastBriefModel} (in the kotlin layer) and
 * {@link CastBriefEntity} (in the entity layer).
 *
 * @author Jieyi
 * @since 12/28/16
 */

@Singleton
public class CastBriefMapper implements IBeanMapper<CastBriefModel, CastBriefEntity> {
    @Inject CastKnownForMapper castKnownForMapper;

    @Inject
    public CastBriefMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CastBriefEntity transformFrom(@NonNull CastBriefModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastBriefModel transformTo(@NonNull CastBriefEntity entity) {
        // We may not use all of information, then we will remove some redundant information.
        List<CastBriefModel.KnownForBean> knownForBean = null != entity.getKnown_for() ?
                Queryable.from(entity.getKnown_for()).map(castKnownForMapper::transformTo).toList() :
                Collections.emptyList();

        return new CastBriefModel(entity.getProfile_path(),
                                  entity.isAdult(),
                                  entity.getId(),
                                  entity.getName(), entity.getPopularity(), knownForBean);
    }
}
