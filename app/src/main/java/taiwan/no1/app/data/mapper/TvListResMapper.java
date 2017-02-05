package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.TvListResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.TvBriefModel;
import taiwan.no1.app.mvp.models.TvListResModel;

/**
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class TvListResMapper implements IBeanMapper<TvListResModel, TvListResEntity> {
    @Inject TvBriefMapper tvBriefMapper;

    @Inject
    public TvListResMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public TvListResEntity transformFrom(@NonNull TvListResModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public TvListResModel transformTo(@NonNull TvListResEntity entity) {
        List<TvBriefModel> tvBriefModels = Queryable.from(entity.getResults())
                                                    .map(this.tvBriefMapper::transformTo)
                                                    .toList();

        return new TvListResModel(entity.getPage(), entity.getTotal_results(), entity.getTotal_pages(), tvBriefModels);
    }
}
