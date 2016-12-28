package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.PopularResEntity;
import taiwan.no1.app.domain.mapper.IEntityMapper;
import taiwan.no1.app.mvp.models.MovieModel;

/**
 * Mapper class used to transform between {@link List<MovieModel>} (in the kotlin layer) and
 * {@link List<PopularResEntity.MovieEntity>} (in the data layer).
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/28/16
 */

@Singleton
public class MovieEntityMapper implements IEntityMapper<List<MovieModel>, List<PopularResEntity.MovieEntity>> {

    @Inject
    public MovieEntityMapper() {
    }

    @NonNull
    @Override
    public List<PopularResEntity.MovieEntity> transformFrom(@NonNull List<MovieModel> model) throws Error {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public List<MovieModel> transformTo(@NonNull List<PopularResEntity.MovieEntity> entities) {
        ArrayList<MovieModel> movieModels = new ArrayList<>();

        for (PopularResEntity.MovieEntity entity : entities) {
            movieModels.add(new MovieModel(entity.getPoster_path(),
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
                                           entity.getVote_average(),
                                           entity.getGenre_ids()));
        }

        return movieModels;
    }
}
