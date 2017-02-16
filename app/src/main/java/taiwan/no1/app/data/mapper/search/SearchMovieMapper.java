package taiwan.no1.app.data.mapper.search;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import taiwan.no1.app.data.entities.search.SearchMovieEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.search.SearchMovieModel;

/**
 * Created by weian on 2017/2/15.
 */

public class SearchMovieMapper implements IBeanMapper<SearchMovieModel, SearchMovieEntity> {
    @NonNull
    @Override
    public SearchMovieEntity transformFrom(@NonNull SearchMovieModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public SearchMovieModel transformTo(@NonNull SearchMovieEntity entity) {
        List<SearchMovieModel.ResultsBean> resultsBean = null != entity.getResults() ?
                Queryable.from(entity.getResults())
                        .map(data -> new SearchMovieModel.ResultsBean(
                                data.getPoster_path(),
                                data.isAdult(),
                                data.getOverview(),
                                data.getRelease_date(),
                                data.getId(),
                                data.getOriginal_title(),
                                data.getOriginal_language(),
                                data.getTitle(),
                                data.getBackdrop_path(),
                                data.getPopularity(),
                                data.getVote_count(),
                                data.isVideo(),
                                data.getVote_average(),
                                null != data.getGenre_ids() ?
                                        new ArrayList<Integer>(data.getGenre_ids()) : null))
                        .toList(): Collections.emptyList();

        return new SearchMovieModel(entity.getPage(),
                entity.getTotal_results(),
                entity.getTotal_pages(),
                resultsBean);
    }
}
