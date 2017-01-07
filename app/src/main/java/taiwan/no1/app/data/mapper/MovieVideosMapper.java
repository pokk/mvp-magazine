package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieVideosEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieVideosModel;

/**
 * @author Jieyi

 * @since 12/31/16
 */

@Singleton
public class MovieVideosMapper implements IBeanMapper<MovieVideosModel, MovieVideosEntity> {

    @Inject
    public MovieVideosMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieVideosEntity transformFrom(@NonNull MovieVideosModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieVideosModel transformTo(@NonNull MovieVideosEntity entity) {
        return new MovieVideosModel(entity.getId(),
                                    entity.getIso_639_1(),
                                    entity.getIso_3166_1(),
                                    entity.getKey(),
                                    entity.getName(),
                                    entity.getSite(),
                                    entity.getSize(),
                                    entity.getType());
    }
}
