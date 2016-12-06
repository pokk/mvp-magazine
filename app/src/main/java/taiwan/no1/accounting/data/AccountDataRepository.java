package taiwan.no1.accounting.data;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.data.mapper.FakeMapper;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class AccountDataRepository implements AccountRepository {
    private final DataStoreFactory dataStoreFactory;
    @Inject FakeMapper fakeMapper;

    @Inject
    AccountDataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @Override
    public Observable<List<FakeEntity>> Fakes() {
        return null;
    }
}
