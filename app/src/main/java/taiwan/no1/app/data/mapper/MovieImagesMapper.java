package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.FilmImagesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.ImageInfoModel;
import taiwan.no1.app.mvp.models.MovieImagesModel;

/**
 * Mapper class used to transform between {@link MovieImagesModel} (in the kotlin layer) an {@link FilmImagesEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class MovieImagesMapper implements IBeanMapper<MovieImagesModel, FilmImagesEntity> {
    @Inject ImageInfoMapper imageInfoMapper;

    @Inject
    public MovieImagesMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public FilmImagesEntity transformFrom(@NonNull MovieImagesModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public MovieImagesModel transformTo(@NonNull FilmImagesEntity entity) {
        List<ImageInfoModel> backdrops = Queryable.from(entity.getBackdrops()).map(this.imageInfoMapper::transformTo).toList();
        List<ImageInfoModel> posters = Queryable.from(entity.getPosters()).map(this.imageInfoMapper::transformTo).toList();
        return new MovieImagesModel(backdrops, posters);
    }
}
