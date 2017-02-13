package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CastBriefEntity;
import taiwan.no1.app.data.entities.SearchListResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastBriefModel;
import taiwan.no1.app.mvp.models.CastListResModel;

/**
 * Mapper class used to transform between {@link CastListResModel} (in the kotlin layer) and
 * {@link SearchListResEntity<CastBriefEntity>} (in the data layer).
 *
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class CastListResMapper implements IBeanMapper<CastListResModel, SearchListResEntity<CastBriefEntity>> {
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
    public SearchListResEntity<CastBriefEntity> transformFrom(@NonNull CastListResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastListResModel transformTo(@NonNull SearchListResEntity<CastBriefEntity> entity) {
        List<CastBriefModel> castBriefBeans = Queryable.from(entity.getResults())
                                                       .map(castBriefMapper::transformTo)
                                                       .toList();

        return new CastListResModel(entity.getPage(),
                                    entity.getTotal_results(),
                                    entity.getTotal_pages(),
                                    castBriefBeans);
    }
}
