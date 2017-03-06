package taiwan.no1.app.data.mapper.tv;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.tv.TvSeasonDetailEntity;
import taiwan.no1.app.data.mapper.FilmCastsMapper;
import taiwan.no1.app.data.mapper.FilmImagesMapper;
import taiwan.no1.app.data.mapper.FilmVideosMapper;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonModel;
import taiwan.no1.app.mvp.models.FilmCastsModel;
import taiwan.no1.app.mvp.models.FilmImagesModel;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel;
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel;

/**
 * Mapper class used to transform between {@link TvSeasonsModel} (in the kotlin layer) and {@link TvSeasonDetailEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 1/21/17.
 */

@Singleton
public class TvSeasonDetailMapper implements IBeanMapper<TvSeasonsModel, TvSeasonDetailEntity> {
    @Inject FilmImagesMapper filmImagesMapper;
    @Inject FilmCastsMapper filmCastsMapper;
    @Inject FilmVideosMapper filmVideosMapper;
    @Inject TvEpisodeDetailMapper tvEpisodeDetailMapper;

    @Inject
    public TvSeasonDetailMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TvSeasonDetailEntity transformFrom(@NonNull TvSeasonsModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvSeasonsModel transformTo(@NonNull TvSeasonDetailEntity entity) {
        FilmImagesModel filmImagesModel = this.filmImagesMapper.transformTo(entity.getImages());
        FilmCastsModel filmCastsModel = this.filmCastsMapper.transformTo(entity.getCredits());
        List<FilmVideoModel> filmVideoModels = null != entity.getVideos().getResults() ?
                Queryable.from(entity.getVideos().getResults()).map(this.filmVideosMapper::transformTo).toList() :
                Collections.emptyList();
        List<TvEpisodesModel> tvEpisodesModels = null != entity.getEpisodes() ?
                Queryable.from(entity.getEpisodes()).map(this.tvEpisodeDetailMapper::transformTo).toList() :
                Collections.emptyList();

        return new TvSeasonsModel(entity.get_id(),
                                  entity.getAir_date(),
                                  entity.getName(),
                                  entity.getOverview(),
                                  entity.getEpisode_count(),
                                  entity.getId(),
                                  entity.getPoster_path(),
                                  entity.getSeason_number(), 0,  // For keeping the tv id to season model.
                                  filmImagesModel,
                                  new CommonModel.VideosBean(filmVideoModels),
                                  filmCastsModel,
                                  tvEpisodesModels);
    }
}
