package taiwan.no1.app.data.mapper.search;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import taiwan.no1.app.data.entities.search.SearchPersonEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.search.SearchPersonModel;

/**
 * Created by weian on 2017/2/16.
 */

public class SearchPersonMapper implements IBeanMapper<SearchPersonModel, SearchPersonEntity> {
    @NonNull
    @Override
    public SearchPersonEntity transformFrom(@NonNull SearchPersonModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public SearchPersonModel transformTo(@NonNull SearchPersonEntity entity) {
        List<SearchPersonModel.ResultsPersonBean.KnownForBean> knownForBean = null != entity.getResults().getClass(SearchPersonModel.ResultsPersonBean.KnownForBean) ?
                Queryable.from(entity.getResults()).map(data -> new SearchPersonModel.ResultsPersonBean.KnownForBean(
                        data.getPoster_path(),
                ))

        List<SearchPersonModel.ResultsPersonBean> resultsPersonBean = null != entity.getResults() ?
                Queryable.from(entity.getResults()).map(data -> new SearchPersonModel.ResultsPersonBean(
                        data.getProfile_path(),
                        data.isAdult(),
                        data.getId(),
                        data.getName(),
                        data.getPopularity(),
                        data.getKnown_for()
                ));

        return new SearchPersonModel(entity.getPage(),
                entity.getTotal_results(),
                entity.getTotal_pages(),);
    }
}
