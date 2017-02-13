package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.TvEpisodeDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.TvEpisodesModel;

/**
 * Mapper class used to transform between {@link TvEpisodesModel} (in the kotlin layer) and {@link TvEpisodeDetailEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 2017/1/21.
 */

@Singleton
public class TVEpisodesMapper implements IBeanMapper<TvEpisodesModel, TvEpisodeDetailEntity> {
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
    public TvEpisodeDetailEntity transformFrom(@NonNull TvEpisodesModel model) {
        return null;
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvEpisodesModel transformTo(@NonNull TvEpisodeDetailEntity entity) {
        List<FilmVideoModel> tvMovieVideosModel = Queryable.from(entity.getVideos().getResults())
                                                           .map(this.tvVideosMapper::transformTo)
                                                           .toList();

        return new TvEpisodesModel(entity.getAir_date(),
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
