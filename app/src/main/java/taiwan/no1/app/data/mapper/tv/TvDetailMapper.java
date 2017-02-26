package taiwan.no1.app.data.mapper.tv;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.tv.TvDetailEntity;
import taiwan.no1.app.data.mapper.BaseBeanMapper;
import taiwan.no1.app.data.mapper.FilmCastsMapper;
import taiwan.no1.app.data.mapper.FilmImagesMapper;
import taiwan.no1.app.data.mapper.FilmVideosMapper;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonModel;
import taiwan.no1.app.mvp.models.FilmCastsModel;
import taiwan.no1.app.mvp.models.FilmImagesModel;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.tv.TvDetailModel;
import taiwan.no1.app.mvp.models.tv.TvListResModel;
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel;

/**
 * Mapper class used to transform between {@link TvDetailModel} (in the kotlin layer) and {@link TvDetailEntity}
 * (in the data layer).
 * <p>
 * Created by weian on 1/12/17.
 */

@Singleton
public class TvDetailMapper implements IBeanMapper<TvDetailModel, TvDetailEntity> {
    @Inject FilmVideosMapper filmVideosMapper;
    @Inject FilmImagesMapper filmImagesMapper;
    @Inject FilmCastsMapper filmCastsMapper;
    @Inject TvListResMapper tvListResMapper;
    //    @Inject MovieListResMapper movieListResMapper;
    @Inject BaseBeanMapper baseBeanMapper;

    @Inject
    public TvDetailMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public TvDetailEntity transformFrom(@NonNull TvDetailModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public TvDetailModel transformTo(@NonNull TvDetailEntity entity) {
        List<FilmVideoModel> filmVideoModels = null != entity.getVideos().getResults() ?
                Queryable.from(entity.getVideos().getResults()).map(filmVideosMapper::transformTo).toList() :
                Collections.emptyList();
        FilmImagesModel filmImagesModel = this.filmImagesMapper.transformTo(entity.getImages());
        TvListResModel tvListResModel = this.tvListResMapper.transformTo(entity.getSimilar());
        FilmCastsModel filmCastsModel = this.filmCastsMapper.transformTo(entity.getCredits());
        List<TvDetailModel.CreatedByBean> createdByBeans = null != entity.getCreated_by() ?
                Queryable.from(entity.getCreated_by())
                         .map(data -> new TvDetailModel.CreatedByBean(data.getId(),
                                                                      data.getName(),
                                                                      data.getProfile_path()))
                         .toList() :
                Collections.emptyList();
        List<CommonModel.BaseBean> genresBeen = null != entity.getGenres() ?
                Queryable.from(entity.getGenres()).map(this.baseBeanMapper::transformTo).toList() :
                Collections.emptyList();
        List<CommonModel.BaseBean> networksBeen = null != entity.getNetworks() ?
                Queryable.from(entity.getNetworks()).map(this.baseBeanMapper::transformTo).toList() :
                Collections.emptyList();
        List<CommonModel.BaseBean> productionCompaniesBeen = null != entity.getProduction_companies() ?
                Queryable.from(entity.getProduction_companies()).map(this.baseBeanMapper::transformTo).toList() :
                Collections.emptyList();
        List<TvSeasonsModel> tvSeasonsModels = null != entity.getSeasons() ?
                Queryable.from(entity.getSeasons())
                         .map(data -> new TvSeasonsModel("",
                                                         data.getAir_date(),
                                                         "",
                                                         "",
                                                         data.getEpisode_count(),
                                                         data.getId(),
                                                         data.getPoster_path(),
                                                         data.getSeason_number(),
                                                         null,
                                                         null,
                                                         null,
                                                         Collections.emptyList()))
                         .toList() :
                Collections.emptyList();

        return new TvDetailModel(entity.getBackdrop_path(),
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
                                 new CommonModel.VideosBean(filmVideoModels),
                                 filmImagesModel, tvListResModel,
                                 filmCastsModel,
                                 createdByBeans,
                                 entity.getEpisode_run_time(),
                                 genresBeen,
                                 entity.getLanguages(),
                                 networksBeen,
                                 entity.getOrigin_country(),
                                 productionCompaniesBeen,
                                 tvSeasonsModels);
    }
}
