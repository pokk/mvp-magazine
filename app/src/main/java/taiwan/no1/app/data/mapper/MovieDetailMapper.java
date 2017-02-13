package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonBean;
import taiwan.no1.app.mvp.models.FilmCastsModel;
import taiwan.no1.app.mvp.models.FilmImagesModel;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.MovieListResModel;

/**
 * Mapper class used to transform between {@link MovieDetailModel} (in the kotlin layer) an {@link MovieDetailEntity}
 * (in the data layer).
 *
 * @author Jieyi
 * @since 12/29/16
 */

@Singleton
public class MovieDetailMapper implements IBeanMapper<MovieDetailModel, MovieDetailEntity> {
    @Inject FilmVideosMapper movieVideosMapper;
    @Inject MovieListResMapper movieListResMapper;
    @Inject FilmCastsMapper movieCastsMapper;
    @Inject FilmImagesMapper movieImagesMapper;

    @Inject
    public MovieDetailMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public MovieDetailEntity transformFrom(@NonNull MovieDetailModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public MovieDetailModel transformTo(@NonNull MovieDetailEntity entity) {
        // We may not use all of information, then we will remove some redundant information.
        List<CommonBean.BaseBean> genresBeen = Queryable.from(entity.getGenres())
                                                        .map(data -> new CommonBean.BaseBean(data.getId(),
                                                                                             data.getName()))
                                                        .toList();
        List<CommonBean.BaseBean> productionCompaniesBeen = Queryable.from(entity.getProduction_companies())
                                                                     .map(data -> new CommonBean.BaseBean(data.getId(),
                                                                                                          data.getName()))
                                                                     .toList();
        List<CommonBean.CountriesBean> productionCountriesBeen = Queryable.from(entity.getProduction_countries())
                                                                          .map(data -> new CommonBean.CountriesBean(data.getIso_3166_1(),
                                                                                                                    data.getName()))
                                                                          .toList();

        List<CommonBean.LanguagesBean> spokenLanguagesBeen = Queryable.from(entity.getSpoken_languages())
                                                                      .map(data -> new CommonBean.LanguagesBean(data.getIso_639_1(),
                                                                                                                data.getName()))
                                                                      .toList();
        List<FilmVideoModel> movieVideosModels = Queryable.from(entity.getVideos().getResults())
                                                          .map(this.movieVideosMapper::transformTo)
                                                          .toList();
        MovieListResModel movieListResModel = this.movieListResMapper.transformTo(entity.getSimilar());
        FilmCastsModel movieCastsModel = this.movieCastsMapper.transformTo(entity.getCasts());
        FilmImagesModel movieImagesModel = this.movieImagesMapper.transformTo(entity.getImages());
        MovieDetailModel.BelongsToCollectionBean belongsToCollectionBean = new MovieDetailModel.BelongsToCollectionBean(
                entity.getBelongs_to_collection().getId(),
                entity.getBelongs_to_collection().getName(),
                entity.getBelongs_to_collection().getPoster_path(),
                entity.getBelongs_to_collection().getBackdrop_path());

        return new MovieDetailModel(entity.isAdult(),
                                    entity.getBackdrop_path(),
                                    null != entity.getBelongs_to_collection() ? belongsToCollectionBean : null,
                                    entity.getBudget(),
                                    entity.getHomepage(),
                                    entity.getId(),
                                    entity.getImdb_id(),
                                    entity.getOriginal_language(),
                                    entity.getOriginal_title(),
                                    entity.getOverview(),
                                    entity.getPopularity(),
                                    entity.getPoster_path(),
                                    entity.getRelease_date(),
                                    entity.getRevenue(),
                                    entity.getRuntime(),
                                    entity.getStatus(),
                                    entity.getTagline(),
                                    entity.getTitle(),
                                    entity.isVideo(),
                                    entity.getVote_average(),
                                    entity.getVote_count(), new CommonBean.VideosBean(movieVideosModels),
                                    movieImagesModel,
                                    movieListResModel,
                                    movieCastsModel,
                                    genresBeen,
                                    productionCompaniesBeen,
                                    productionCountriesBeen,
                                    spokenLanguagesBeen);
    }
}
