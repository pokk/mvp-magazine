package taiwan.no1.accounting.data.source;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link FakeEntity}.
     */
    Observable<FakeModel> createEntity();
}
