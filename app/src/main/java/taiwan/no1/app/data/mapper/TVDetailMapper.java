package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.MovieImagesModel;
import taiwan.no1.app.mvp.models.MovieListResModel;
import taiwan.no1.app.mvp.models.MovieVideosModel;
import taiwan.no1.app.mvp.models.TvDetailModel;

/**
 * Created by weian on 2017/1/12.
 */

@Singleton
public class TVDetailMapper implements IBeanMapper<TvDetailModel, TVDetailEntity> {
    @Inject MovieVideosMapper tvVideosMapper;
    @Inject MovieImagesMapper tvImagesMapper;
    @Inject MovieListResMapper tvListResMapper;


    @Inject
    public TVDetailMapper() {
    }

    @NonNull
    @Override
    public TVDetailEntity transformFrom(@NonNull TvDetailModel model) {
        return null;
    }

    @NonNull
    @Override
    public TvDetailModel transformTo(@NonNull TVDetailEntity entity) {
        List<MovieVideosModel> tvVideosModels = Queryable.from(entity.getVideos().getResults())
                .map(this.tvVideosMapper::transformTo)
                .toList();

        MovieImagesModel tvImagesModel = this.tvImagesMapper.transformTo(entity.getImages());

        MovieListResModel tvListResModel = this.tvListResMapper.transformTo(entity.getSimilar());

        List<TvDetailModel.CreatedByBean> tvCreatedByBean = Queryable.from(entity.getCreated_by())
               .map(data -> new TvDetailModel.CreatedByBean())
                .toList();

        return new TvDetailModel( entity.getBackdrop_path(),
                entity.getFirst_air_date(),
                entity.getHomepage(),
                entity.getId(),
                entity.isIn_production(),
                entity.getLast_air_date(),
                entity.getName(),
                entity.getNumber_of_episodes(),
                entity.getNumber_of_seasons(),
                entity.getOriginal_language(),
                entity.getOriginal_name(),
                entity.getOverview(),
                entity.getPopularity(),
                entity.getPoster_path(),
                entity.getStatus(),
                entity.getType(),
                entity.getVote_average(),
                entity.getVote_count(),
                new MovieDetailModel.VideosBean(tvVideosModels),
                tvImagesModel,
                tvListResModel,
                tvCreatedByBean,
                entity.getEpisode_run_time(),
                entity.getGenres(),
                entity.getLanguages(),
                entity.getNetworks(),
                entity.getOrigin_country(),
                entity.getProduction_companies(),
                entity.getSeasons());
    }
}