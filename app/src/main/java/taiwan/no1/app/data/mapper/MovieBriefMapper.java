package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.MovieBriefModel;

/**
 * Mapper class used to transform between {@link MovieBriefModel} (in the kotlin layer) and
 * {@link MovieBriefEntity} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/28/16
 */

@Singleton
public class MovieBriefMapper implements IBeanMapper<MovieBriefModel, MovieBriefEntity> {

    @Inject
    public MovieBriefMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public MovieBriefEntity transformFrom(@NonNull MovieBriefModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public MovieBriefModel transformTo(@NonNull MovieBriefEntity entity) {
        // We may not use all of information, then we will remove some redundant information.
        return new MovieBriefModel(entity.getPoster_path(),
                                   entity.isAdult(),
                                   entity.getOverview(),
                                   entity.getRelease_date(),
                                   entity.getId(),
                                   entity.getOriginal_title(),
                                   entity.getOriginal_language(),
                                   entity.getTitle(),
                                   entity.getBackdrop_path(),
                                   entity.getPopularity(),
                                   entity.getVote_count(),
                                   entity.isVideo(),
                                   entity.getVote_average(), new ArrayList<>(entity.getGenre_ids()));
    }
}
