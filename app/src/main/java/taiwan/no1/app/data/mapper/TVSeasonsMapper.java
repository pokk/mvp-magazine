package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.TVSeasonsEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.TrstSeasonsModel;

/**
 * Mapper class used to transform between {@link TrstSeasonsModel} (in the kotlin layer) and {@link TVSeasonsEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 2017/1/21.
 */

@Singleton
public class TVSeasonsMapper implements IBeanMapper<TrstSeasonsModel, TVSeasonsEntity> {
    @Inject
    public TVSeasonsMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TVSeasonsEntity transformFrom(@NonNull TrstSeasonsModel model) {
        return null;
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TrstSeasonsModel transformTo(@NonNull TVSeasonsEntity entity) {
        List<TrstSeasonsModel.EpisodesBean> tvEpisodesBean = Queryable.from(entity.getEpisodes())
                                                                      .map(data -> new TrstSeasonsModel.EpisodesBean())
                                                                      .toList();

        return new TrstSeasonsModel(entity.get_id(),
                                    entity.getAir_date(),
                                    entity.getName(),
                                    entity.getOverview(),
                                    entity.getId(),
                                    entity.getPoster_path(),
                                    entity.getSeason_number(),
                                    tvEpisodesBean);
    }
}
