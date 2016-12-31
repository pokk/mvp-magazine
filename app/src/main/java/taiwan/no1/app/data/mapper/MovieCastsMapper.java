package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieCastsEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieCastsModel;

/**
 * Mapper class used to transform between {@link MovieCastsModel} (in the kotlin layer) and
 * {@link MovieCastsEntity} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/28/16
 */

@Singleton
public class MovieCastsMapper implements IBeanMapper<MovieCastsModel, MovieCastsEntity> {

    @Inject
    public MovieCastsMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieCastsEntity transformFrom(@NonNull MovieCastsModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieCastsModel transformTo(@NonNull MovieCastsEntity entity) {
        List<MovieCastsModel.CastBean> castBeen = new ArrayList<>();
        for (MovieCastsEntity.CastBean bean : entity.getCast()) {
            castBeen.add(new MovieCastsModel.CastBean(bean.getCast_id(),
                                                      bean.getCharacter(),
                                                      bean.getCredit_id(),
                                                      bean.getId(),
                                                      bean.getName(),
                                                      bean.getOrder(),
                                                      bean.getProfile_path()));
        }
        List<MovieCastsModel.CrewBean> crewBeen = new ArrayList<>();
        for (MovieCastsEntity.CrewBean bean : entity.getCrew()) {
            crewBeen.add(new MovieCastsModel.CrewBean(bean.getCredit_id(),
                                                      bean.getDepartment(),
                                                      bean.getId(),
                                                      bean.getJob(),
                                                      bean.getName(),
                                                      bean.getProfile_path()));
        }

        return new MovieCastsModel(castBeen, crewBeen);
    }
}
