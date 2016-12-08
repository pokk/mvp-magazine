package taiwan.no1.accounting.data.source;

import android.support.annotation.NonNull;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * Interface that represents a data store from where data is retrieved.
 * 
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link FakeEntity}.
     */
    Observable<FakeEntity> createEntity(@NonNull final FakeModel model);
}
