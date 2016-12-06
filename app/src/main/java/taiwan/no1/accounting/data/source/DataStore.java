package taiwan.no1.accounting.data.source;

import java.util.List;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public interface DataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link FakeEntity}.
     */
    Observable<List<FakeEntity>> EntityList();
}
