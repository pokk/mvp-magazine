package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieImagesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.ImageInfoModel;
import taiwan.no1.app.mvp.models.MovieImagesModel;

/**
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class MovieImagesMapper implements IBeanMapper<MovieImagesModel, MovieImagesEntity> {
    @Inject ImageInfoMapper imageInfoMapper;

    @Inject
    public MovieImagesMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieImagesEntity transformFrom(@NonNull MovieImagesModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieImagesModel transformTo(@NonNull MovieImagesEntity entity) {
        List<ImageInfoModel> backdrops = Queryable.from(entity.getBackdrops())
                                                  .map(this.imageInfoMapper::transformTo)
                                                  .toList();
        List<ImageInfoModel> posters = Queryable.from(entity.getPosters())
                                                .map(this.imageInfoMapper::transformTo)
                                                .toList();
        return new MovieImagesModel(backdrops, posters);
    }
}
