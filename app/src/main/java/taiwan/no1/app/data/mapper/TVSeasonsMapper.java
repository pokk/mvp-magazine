package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.TvSeasonDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.TvSeasonsModel;

/**
 * Mapper class used to transform between {@link TvSeasonsModel} (in the kotlin layer) and {@link TvSeasonDetailEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 2017/1/21.
 */

@Singleton
public class TVSeasonsMapper implements IBeanMapper<TvSeasonsModel, TvSeasonDetailEntity> {
    @Inject
    public TVSeasonsMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TvSeasonDetailEntity transformFrom(@NonNull TvSeasonsModel model) {
        return null;
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvSeasonsModel transformTo(@NonNull TvSeasonDetailEntity entity) {
        List<TvSeasonsModel.EpisodesBean> tvEpisodesBean = Queryable.from(entity.getEpisodes())
                                                                    .map(data -> new TvSeasonsModel.EpisodesBean())
                                                                    .toList();

        return new TvSeasonsModel(entity.get_id(),
                                  entity.getAir_date(),
                                  entity.getName(),
                                  entity.getOverview(),
                                  entity.getId(),
                                  entity.getPoster_path(),
                                  entity.getSeason_number(),
                                  tvEpisodesBean);
    }
}
