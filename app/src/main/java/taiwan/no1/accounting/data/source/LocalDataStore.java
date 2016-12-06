package taiwan.no1.accounting.data.source;

import java.util.List;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class LocalDataStore implements DataStore {
    @Override
    public Observable<List<FakeEntity>> EntityList() {
        return null;
    }
}
