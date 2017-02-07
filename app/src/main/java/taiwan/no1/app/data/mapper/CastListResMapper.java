package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CastListResEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CastListResModel;

/**
 * @author Jieyi
 * @since 1/1/17
 */

@Singleton
public class CastListResMapper implements IBeanMapper<CastListResModel, CastListResEntity> {
    @Inject CastKnownForMapper castKnownForMapper;

    @Inject
    public CastListResMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public CastListResEntity transformFrom(@NonNull CastListResModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public CastListResModel transformTo(@NonNull CastListResEntity entity) {
        List<CastListResModel.CastBriefBean> castBriefBeans = Queryable.from(entity.getResults())
                                                                       .map(data -> new CastListResModel.CastBriefBean(
                                                                               data.getProfile_path(),
                                                                               data.isAdult(),
                                                                               data.getId(),
                                                                               data.getName(), data.getPopularity()))
                                                                       // TODO: 2/3/17 In the furture we may use it.
                                                                       //                                                                               Queryable.from(data.getKnown_for())
                                                                       //                                                                                        .map(castKnownForMapper::transformTo)
                                                                       //                                                                                        .toList()))
                                                                       .toList();

        return new CastListResModel(entity.getPage(),
                                    entity.getTotal_results(),
                                    entity.getTotal_pages(),
                                    castBriefBeans);
    }
}
