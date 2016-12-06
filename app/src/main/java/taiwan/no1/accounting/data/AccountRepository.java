package taiwan.no1.accounting.data;

import java.util.List;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;

/**
 * Created by lekaha on 5/29/16.
 */
public interface AccountRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link FakeEntity}.
     */
    Observable<List<FakeEntity>> Fakes();
}
