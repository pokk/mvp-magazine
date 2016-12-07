package taiwan.no1.accounting.data.source;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Override
    public Observable<FakeEntity> createEntity() {
        return null;
    }
}
