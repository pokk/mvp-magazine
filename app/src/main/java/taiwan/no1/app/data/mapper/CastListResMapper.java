package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastListResModel;

/**
 * Mapper class used to transform between {@link CastListResModel} (in the kotlin layer) and {@link CastListResEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class CastListResMapper implements IBeanMapper<CastListResModel, CastListResEntity> {
    @Inject CastKnownForMapper castKnownForMapper;

    @Inject
    public CastListResMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CastListResEntity transformFrom(@NonNull CastListResModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CastListResModel transformTo(@NonNull CastListResEntity entity) {
        List<CastListResModel.CastBriefBean> castBriefBeans = Queryable.from(entity.getResults())
                                                                       .map(data -> new CastListResModel.CastBriefBean(data.getProfile_path(),
                                                                                                                       data.isAdult(),
                                                                                                                       data.getId(),
                                                                                                                       data.getName(),
                                                                                                                       data.getPopularity(),
                                                                                                                       Queryable.from(
                                                                                                                               data.getKnown_for())
                                                                                                                                .map(castKnownForMapper::transformTo)
                                                                                                                                .toList()))
                                                                       .toList();

        return new CastListResModel(entity.getPage(), entity.getTotal_results(), entity.getTotal_pages(), castBriefBeans);
    }
}
