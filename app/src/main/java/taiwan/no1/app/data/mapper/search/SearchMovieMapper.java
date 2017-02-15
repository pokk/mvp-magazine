package taiwan.no1.app.data.mapper.search;

import android.support.annotation.NonNull;

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
        return null;
    }

    @NonNull
    @Override
    public SearchMovieModel transformTo(@NonNull SearchMovieEntity entity) {
        return null;
    }
}
