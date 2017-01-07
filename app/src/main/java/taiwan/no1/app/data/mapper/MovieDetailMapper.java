package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieCastsModel;
import taiwan.no1.app.mvp.models.MovieDetailModel;
import taiwan.no1.app.mvp.models.MovieDetailModel.GenresBean;
import taiwan.no1.app.mvp.models.MovieDetailModel.ProductionCompaniesBean;
import taiwan.no1.app.mvp.models.MovieDetailModel.ProductionCountriesBean;
import taiwan.no1.app.mvp.models.MovieDetailModel.SpokenLanguagesBean;
import taiwan.no1.app.mvp.models.MovieImagesModel;
import taiwan.no1.app.mvp.models.MovieListResModel;
import taiwan.no1.app.mvp.models.MovieVideosModel;

/**
 * Mapper class used to transform between {@link MovieDetailModel} (in the kotlin layer) an
 * {@link MovieDetailEntity} (in the data layer).
 *
 * @author Jieyi

 * @since 12/29/16
 */

@Singleton
public class MovieDetailMapper implements IBeanMapper<MovieDetailModel, MovieDetailEntity> {
    @Inject MovieVideosMapper movieVideosMapper;
    @Inject MovieListResMapper movieListResMapper;
    @Inject MovieCastsMapper movieCastsMapper;
    @Inject MovieImagesMapper movieImagesMapper;

    @Inject
    public MovieDetailMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieDetailEntity transformFrom(@NonNull MovieDetailModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieDetailModel transformTo(@NonNull MovieDetailEntity entity) {
        // We may not use all of information, then we will remove some redundant information.
        List<GenresBean> genresBeen = Queryable.from(entity.getGenres())
                                               .map(data -> new MovieDetailModel.GenresBean(data.getId(),
                                                                                            data.getName()))
                                               .toList();
        List<ProductionCompaniesBean> productionCompaniesBeen = Queryable.from(entity.getProduction_companies())
                                                                         .map(data -> new MovieDetailModel.ProductionCompaniesBean(
                                                                                 data.getName(),
                                                                                 data.getId()))
                                                                         .toList();
        List<ProductionCountriesBean> productionCountriesBeen = Queryable.from(entity.getProduction_countries())
                                                                         .map(data -> new MovieDetailModel.ProductionCountriesBean(
                                                                                 data.getIso_3166_1(),
                                                                                 data.getName()))
                                                                         .toList();

        List<SpokenLanguagesBean> spokenLanguagesBeen = Queryable.from(entity.getSpoken_languages())
                                                                 .map(data -> new MovieDetailModel.SpokenLanguagesBean(
                                                                         data.getIso_639_1(),
                                                                         data.getName()))
                                                                 .toList();
        List<MovieVideosModel> movieVideosModels = Queryable.from(entity.getVideos().getResults())
                                                            .map(this.movieVideosMapper::transformTo)
                                                            .toList();
        MovieListResModel movieListResModel = this.movieListResMapper.transformTo(entity.getSimilar());
        MovieCastsModel movieCastsModel = this.movieCastsMapper.transformTo(entity.getCasts());
        MovieImagesModel movieImagesModel = this.movieImagesMapper.transformTo(entity.getImages());

        return new MovieDetailModel(entity.isAdult(),
                                    entity.getBackdrop_path(),
                                    null != entity.getBelongs_to_collection() ?
                                            new MovieDetailModel.BelongsToCollectionBean(entity.getBelongs_to_collection()
                                                                                               .getId(),
                                                                                         entity.getBelongs_to_collection()
                                                                                               .getName(),
                                                                                         entity.getBelongs_to_collection()
                                                                                               .getPoster_path(),
                                                                                         entity.getBelongs_to_collection()
                                                                                               .getBackdrop_path()) :
                                            null,
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
                                    entity.getVote_count(),
                                    new MovieDetailModel.VideosBean(movieVideosModels),
                                    movieImagesModel,
                                    movieListResModel,
                                    movieCastsModel,
                                    genresBeen,
                                    productionCompaniesBeen,
                                    productionCountriesBeen,
                                    spokenLanguagesBeen);
    }
}
