package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieDetailModel;

/**
 * Mapper class used to transform between {@link MovieDetailModel} (in the kotlin layer) an
 * {@link MovieDetailEntity} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/29/16
 */

@Singleton
public class MovieDetailMapper implements IBeanMapper<MovieDetailModel, MovieDetailEntity> {

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
        List<MovieDetailModel.GenresBean> genresBeen = new ArrayList<>();
        for (MovieDetailEntity.GenresBean bean : entity.getGenres()) {
            genresBeen.add(new MovieDetailModel.GenresBean(bean.getId(), bean.getName()));
        }
        List<MovieDetailModel.ProductionCompaniesBean> productionCompaniesBeen = new ArrayList<>();
        for (MovieDetailEntity.ProductionCompaniesBean bean : entity.getProduction_companies()) {
            productionCompaniesBeen.add(new MovieDetailModel.ProductionCompaniesBean(bean.getName(),
                                                                                     bean.getId()));
        }
        List<MovieDetailModel.ProductionCountriesBean> productionCountriesBeen = new ArrayList<>();
        for (MovieDetailEntity.ProductionCountriesBean bean : entity.getProduction_countries()) {
            productionCountriesBeen.add(new MovieDetailModel.ProductionCountriesBean(bean.getIso_3166_1(),
                                                                                     bean.getName()));
        }
        List<MovieDetailModel.SpokenLanguagesBean> spokenLanguagesBeen = new ArrayList<>();
        for (MovieDetailEntity.SpokenLanguagesBean bean : entity.getSpoken_languages()) {
            spokenLanguagesBeen.add(new MovieDetailModel.SpokenLanguagesBean(bean.getIso_639_1(),
                                                                             bean.getName()));
        }

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
                                    genresBeen,
                                    productionCompaniesBeen,
                                    productionCountriesBeen,
                                    spokenLanguagesBeen);
    }
}
