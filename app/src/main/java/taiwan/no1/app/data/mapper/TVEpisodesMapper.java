package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.TVEpisodesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.MovieVideosModel;
import taiwan.no1.app.mvp.models.TestEpisodesModel;

/**
 * Mapper class used to transform between {@link TestEpisodesModel} (in the kotlin layer) and {@link TVEpisodesEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 2017/1/21.
 */

@Singleton
public class TVEpisodesMapper implements IBeanMapper<TestEpisodesModel, TVEpisodesEntity> {
    @Inject MovieVideosMapper tvVideosMapper;

    @Inject
    public TVEpisodesMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TVEpisodesEntity transformFrom(@NonNull TestEpisodesModel model) {
        return null;
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TestEpisodesModel transformTo(@NonNull TVEpisodesEntity entity) {
        List<MovieVideosModel> tvMovieVideosModel = Queryable.from(entity.getVideos().getResults())
                                                             .map(this.tvVideosMapper::transformTo)
                                                             .toList();

        return new TestEpisodesModel(entity.getAir_date(),
                                     entity.getEpisode_number(),
                                     entity.getName(),
                                     entity.getOverview(),
                                     entity.getId(),
                                     entity.getProduction_code(),
                                     entity.getSeason_number(),
                                     entity.getStill_path(),
                                     entity.getVote_count(),
                                     new MovieDetailModel.VideosBean(tvMovieVideosModel));
    }
}
