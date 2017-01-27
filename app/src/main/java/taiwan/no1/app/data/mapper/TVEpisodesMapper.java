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
import taiwan.no1.app.mvp.models.TVEpisodesModel;

/**
 * Created by weian on 2017/1/21.
 */

@Singleton
public class TVEpisodesMapper  implements IBeanMapper<TVEpisodesModel, TVEpisodesEntity> {
    @Inject MovieVideosMapper tvVideosMapper;

    @Inject
    public TVEpisodesMapper() {
    }

    @NonNull
    @Override
    public TVEpisodesEntity transformFrom(@NonNull TVEpisodesModel model) {
        return null;
    }

    @NonNull
    @Override
    public TVEpisodesModel transformTo(@NonNull TVEpisodesEntity entity) {
        // Todo
        List<MovieVideosModel> tvMovieVideosModel = Queryable.from(entity.getVideos().getResults())
                .map(this.tvVideosMapper::transformTo)
                .toList();

        return new TVEpisodesModel(entity.getAir_date(),
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
