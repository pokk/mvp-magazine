package taiwan.no1.app.data.mapper.movie;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.movie.MovieDetailEntity;
import taiwan.no1.app.data.mapper.BaseBeanMapper;
import taiwan.no1.app.data.mapper.FilmCastsMapper;
import taiwan.no1.app.data.mapper.FilmImagesMapper;
import taiwan.no1.app.data.mapper.FilmVideosMapper;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonModel;
import taiwan.no1.app.mvp.models.FilmCastsModel;
import taiwan.no1.app.mvp.models.FilmImagesModel;
import taiwan.no1.app.mvp.models.FilmVideoModel;
import taiwan.no1.app.mvp.models.movie.MovieDetailModel;
import taiwan.no1.app.mvp.models.movie.MovieListResModel;

/**
 * Mapper class used to transform between {@link MovieDetailModel} (in the kotlin layer) an {@link MovieDetailEntity}
 * (in the data layer).
 *
 * @author Jieyi
 * @since 12/29/16
 */

@Singleton
public class MovieDetailMapper implements IBeanMapper<MovieDetailModel, MovieDetailEntity> {
    @Inject FilmVideosMapper filmVideosMapper;
    @Inject MovieListResMapper movieListResMapper;
    @Inject FilmCastsMapper filmCastsMapper;
    @Inject FilmImagesMapper filmImagesMapper;
    @Inject BaseBeanMapper baseBeanMapper;

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
        List<CommonModel.BaseBean> genresBeen = null != entity.getGenres() ?
                Queryable.from(entity.getGenres()).map(this.baseBeanMapper::transformTo).toList() :
                Collections.emptyList();
        List<CommonModel.BaseBean> productionCompaniesBeen = null != entity.getProduction_companies() ?
                Queryable.from(entity.getProduction_companies()).map(this.baseBeanMapper::transformTo).toList() :
                Collections.emptyList();
        List<CommonModel.CountriesBean> productionCountriesBeen = null != entity.getProduction_countries() ?
                Queryable.from(entity.getProduction_countries())
                         .map(data -> new CommonModel.CountriesBean(data.getIso_3166_1(), data.getName()))
                         .toList() :
                Collections.emptyList();
        List<CommonModel.LanguagesBean> spokenLanguagesBeen = null != entity.getSpoken_languages() ?
                Queryable.from(entity.getSpoken_languages())
                         .map(data -> new CommonModel.LanguagesBean(data.getIso_639_1(), data.getName()))
                         .toList() :
                Collections.emptyList();
        List<FilmVideoModel> movieVideosModels = null != entity.getVideos().getResults() ?
                Queryable.from(entity.getVideos().getResults()).map(this.filmVideosMapper::transformTo).toList() :
                Collections.emptyList();
        MovieListResModel movieListResModel = this.movieListResMapper.transformTo(entity.getSimilar());
        FilmCastsModel movieCastsModel = this.filmCastsMapper.transformTo(entity.getCasts());
        FilmImagesModel movieImagesModel = this.filmImagesMapper.transformTo(entity.getImages());
        MovieDetailModel.BelongsToCollectionBean belongsToCollectionBean = null != entity.getBelongs_to_collection() ?
                new MovieDetailModel.BelongsToCollectionBean(entity.getBelongs_to_collection().getId(),
                                                             entity.getBelongs_to_collection().getName(),
                                                             entity.getBelongs_to_collection().getPoster_path(),
                                                             entity.getBelongs_to_collection().getBackdrop_path()) :
                new MovieDetailModel.BelongsToCollectionBean(0, "", "", "");

        return new MovieDetailModel(entity.isAdult(),
                                    entity.getBackdrop_path(),
                                    belongsToCollectionBean,
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
                                    new CommonModel.VideosBean(movieVideosModels),
                                    movieImagesModel,
                                    movieListResModel,
                                    movieCastsModel,
                                    genresBeen,
                                    productionCompaniesBeen,
                                    productionCountriesBeen,
                                    spokenLanguagesBeen);
    }
}
