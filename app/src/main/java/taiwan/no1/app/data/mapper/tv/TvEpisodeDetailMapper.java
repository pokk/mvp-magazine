package taiwan.no1.app.data.mapper.tv;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.FilmCastsEntity;
import taiwan.no1.app.data.entities.tv.TvEpisodeDetailEntity;
import taiwan.no1.app.data.mapper.FilmCastsMapper;
import taiwan.no1.app.data.mapper.FilmVideosMapper;
import taiwan.no1.app.data.mapper.ImageProfileMapper;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonModel;
import taiwan.no1.app.mvp.models.FilmCastsModel;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.ImageProfileModel;
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel;

/**
 * Mapper class used to transform between {@link TvEpisodesModel} (in the kotlin layer) and {@link TvEpisodeDetailEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 1/21/17.
 */

@Singleton
public class TvEpisodeDetailMapper implements IBeanMapper<TvEpisodesModel, TvEpisodeDetailEntity> {
    @Inject ImageProfileMapper imageProfileMapper;
    @Inject FilmVideosMapper filmVideosMapper;
    @Inject FilmCastsMapper filmCastsMapper;

    @Inject
    public TvEpisodeDetailMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TvEpisodeDetailEntity transformFrom(@NonNull TvEpisodesModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvEpisodesModel transformTo(@NonNull TvEpisodeDetailEntity entity) {
        List<ImageProfileModel> imageProfileModels = null != entity.getImages().getStills() ?
                Queryable.from(entity.getImages().getStills()).map(this.imageProfileMapper::transformTo).toList() :
                Collections.emptyList();
        List<FilmVideoModel> tvMovieVideosModel = null != entity.getVideos().getResults() ?
                Queryable.from(entity.getVideos().getResults()).map(this.filmVideosMapper::transformTo).toList() :
                Collections.emptyList();
        FilmCastsModel filmCastsModel = this.filmCastsMapper.transformTo(entity.getCredits());
        // Using FileCastsEntity mapping to get the casts and the crews class.
        FilmCastsEntity tempEntity = new FilmCastsEntity();
        tempEntity.setCast(null != entity.getGuest_stars() ? entity.getGuest_stars() : Collections.emptyList());
        tempEntity.setCrew(null != entity.getCrew() ? entity.getCrew() : Collections.emptyList());
        FilmCastsModel tempFilmCastsModel = this.filmCastsMapper.transformTo(tempEntity);

        return new TvEpisodesModel(entity.getAir_date(),
                                   entity.getEpisode_number(),
                                   entity.getName(),
                                   entity.getOverview(),
                                   entity.getId(),
                                   entity.getProduction_code(),
                                   entity.getSeason_number(),
                                   entity.getStill_path(),
                                   entity.getVote_count(),
                                   new TvEpisodesModel.ImageBean(imageProfileModels),
                                   new CommonModel.VideosBean(tvMovieVideosModel),
                                   filmCastsModel,
                                   tempFilmCastsModel.getCrew(),
                                   tempFilmCastsModel.getCast());
    }
}
