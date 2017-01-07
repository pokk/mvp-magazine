package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieCastsEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieCastsModel;
import taiwan.no1.app.mvp.models.MovieCastsModel.CastBean;
import taiwan.no1.app.mvp.models.MovieCastsModel.CrewBean;

/**
 * Mapper class used to transform between {@link MovieCastsModel} (in the kotlin layer) and
 * {@link MovieCastsEntity} (in the data layer).
 *
 * @author Jieyi

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
        List<CastBean> castBeen = Queryable.from(entity.getCast())
                                           .map(data -> new MovieCastsModel.CastBean(data.getCast_id(),
                                                                                     data.getCharacter(),
                                                                                     data.getCredit_id(),
                                                                                     data.getId(),
                                                                                     data.getName(),
                                                                                     data.getOrder(),
                                                                                     data.getProfile_path()))
                                           .toList();

        List<CrewBean> crewBeen = Queryable.from(entity.getCrew())
                                           .map(data -> new MovieCastsModel.CrewBean(data.getCredit_id(),
                                                                                     data.getDepartment(),
                                                                                     data.getId(),
                                                                                     data.getJob(),
                                                                                     data.getName(),
                                                                                     data.getProfile_path()))
                                           .toList();

        return new MovieCastsModel(castBeen, crewBeen);
    }
}
