package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.SearchListResEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.TvBriefModel;
import taiwan.no1.app.mvp.models.TvListResModel;

/**
 * Mapper class used to transform between {@link TvListResModel} (in the kotlin layer) and
 * {@link SearchListResEntity<TvBriefEntity>} (in the data layer).
 *
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class TvListResMapper implements IBeanMapper<TvListResModel, SearchListResEntity<TvBriefEntity>> {
    @Inject TvBriefMapper tvBriefMapper;

    @Inject
    public TvListResMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public SearchListResEntity<TvBriefEntity> transformFrom(@NonNull TvListResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvListResModel transformTo(@NonNull SearchListResEntity<TvBriefEntity> entity) {
        List<TvBriefModel> tvBriefModels = Queryable.from(entity.getResults())
                                                    .map(this.tvBriefMapper::transformTo)
                                                    .toList();

        return new TvListResModel(entity.getPage(), entity.getTotal_results(), entity.getTotal_pages(), tvBriefModels);
    }
}
