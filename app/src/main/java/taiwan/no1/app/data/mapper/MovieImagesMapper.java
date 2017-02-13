package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.FilmImagesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.FilmImagesModel;
import taiwan.no1.app.mvp.models.ImageProfileModel;

/**
 * Mapper class used to transform between {@link FilmImagesModel} (in the kotlin layer) an {@link FilmImagesEntity}
 * (in the data layer).
 * 
 * @author Jieyi
 * @since 12/31/16
 */

@Singleton
public class MovieImagesMapper implements IBeanMapper<FilmImagesModel, FilmImagesEntity> {
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
    public FilmImagesEntity transformFrom(@NonNull FilmImagesModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public FilmImagesModel transformTo(@NonNull FilmImagesEntity entity) {
        List<ImageProfileModel> backdrops = Queryable.from(entity.getBackdrops())
                                                     .map(this.imageInfoMapper::transformTo)
                                                     .toList();
        List<ImageProfileModel> posters = Queryable.from(entity.getPosters())
                                                   .map(this.imageInfoMapper::transformTo)
                                                   .toList();
        return new FilmImagesModel(backdrops, posters);
    }
}
