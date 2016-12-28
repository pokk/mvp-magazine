package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.PopularResEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 * 
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a {@link PopularResEntity}.
     */
    @Nullable
    Observable<PopularResEntity> getPopularMovieEntities(final int page);
}
