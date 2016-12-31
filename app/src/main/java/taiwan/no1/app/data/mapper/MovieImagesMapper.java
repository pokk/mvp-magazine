package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieImagesEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieImagesModel;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */

@Singleton
public class MovieImagesMapper implements IBeanMapper<MovieImagesModel, MovieImagesEntity> {

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
        List<MovieImagesModel.BackdropsBean> backdropsBeen = new ArrayList<>();
        for (MovieImagesEntity.BackdropsBean bean : entity.getBackdrops()) {
            backdropsBeen.add(new MovieImagesModel.BackdropsBean(bean.getAspect_ratio(),
                                                                 bean.getFile_path(),
                                                                 bean.getHeight(),
                                                                 bean.getIso_639_1(),
                                                                 bean.getVote_average(),
                                                                 bean.getVote_count(),
                                                                 bean.getWidth()));
        }
        List<MovieImagesModel.PostersBean> postersBeen = new ArrayList<>();
        for (MovieImagesEntity.PostersBean bean : entity.getPosters()) {
            postersBeen.add(new MovieImagesModel.PostersBean(bean.getAspect_ratio(),
                                                             bean.getFile_path(),
                                                             bean.getHeight(),
                                                             bean.getIso_639_1(),
                                                             bean.getVote_average(),
                                                             bean.getVote_count(),
                                                             bean.getWidth()));
        }

        return new MovieImagesModel(backdropsBeen, postersBeen);
    }
}
