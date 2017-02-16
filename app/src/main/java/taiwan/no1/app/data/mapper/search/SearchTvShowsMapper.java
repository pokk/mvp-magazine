package taiwan.no1.app.data.mapper.search;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import taiwan.no1.app.data.entities.search.SearchTvShowsEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.search.SearchTvShowsModel;

/**
 * Created by weian on 2017/2/16.
 */

public class SearchTvShowsMapper implements IBeanMapper<SearchTvShowsModel, SearchTvShowsEntity> {

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public SearchTvShowsEntity transformFrom(@NonNull SearchTvShowsModel model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public SearchTvShowsModel transformTo(@NonNull SearchTvShowsEntity entity) {
        List<SearchTvShowsModel.ResultsTvShowsBean> resultsTvShowsBean =
                null != entity.getResults() ?
                        Queryable.from(entity.getResults())
                                .map(data -> new SearchTvShowsModel.ResultsTvShowsBean(
                                        data.getPoster_path(),
                                        data.getPopularity(),
                                        data.getId(),
                                        data.getBackdrop_path(),
                                        data.getVote_average(),
                                        data.getOverview(),
                                        data.getFirst_air_date(),
                                        data.getOriginal_language(),
                                        data.getVote_count(),
                                        data.getName(),
                                        data.getOriginal_name(),
                                        null != data.getOrigin_country() ?
                                                new ArrayList<String>(data.getOrigin_country()) : null,
                                        null != data.getGenre_ids() ?
                                                new ArrayList<Integer>(data.getGenre_ids()) : null))
                                .toList(): Collections.emptyList();


        return new SearchTvShowsModel(entity.getPage(),
                entity.getTotal_results(),
                entity.getTotal_pages(),
                resultsTvShowsBean);
    }
}
