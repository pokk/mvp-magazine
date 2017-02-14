package taiwan.no1.app.data.mapper.cast;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.ListResEntity;
import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.cast.CastBriefModel;
import taiwan.no1.app.mvp.models.cast.CastListResModel;

/**
 * Mapper class used to transform between {@link CastListResModel} (in the kotlin layer) and
 * {@link ListResEntity <CastBriefEntity>} (in the data layer).
 *
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class CastListResMapper implements IBeanMapper<CastListResModel, ListResEntity<CastBriefEntity>> {
    @Inject CastBriefMapper castBriefMapper;

    @Inject
    public CastListResMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public ListResEntity<CastBriefEntity> transformFrom(@NonNull CastListResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastListResModel transformTo(@NonNull ListResEntity<CastBriefEntity> entity) {
        List<CastBriefModel> castBriefBeans = Queryable.from(entity.getResults())
                                                       .map(castBriefMapper::transformTo)
                                                       .toList();

        return new CastListResModel(entity.getPage(),
                                    entity.getTotal_results(),
                                    entity.getTotal_pages(),
                                    castBriefBeans);
    }
}
