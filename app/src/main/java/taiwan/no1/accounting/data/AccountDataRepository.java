package taiwan.no1.accounting.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.data.mapper.FakeMapper;
import taiwan.no1.accounting.data.source.factory.DataStoreFactory;
import taiwan.no1.accounting.mvp.models.FakeModel;
import taiwan.no1.accounting.utilies.AppLog;

/**
 * Higher data model convert to low layer pure entities for fitting the repository.
 *
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
    public Observable<FakeEntity> CreateFakes(@NonNull FakeModel fakeModel) {
        return dataStoreFactory.create().createEntity().map(model -> {
            AppLog.INSTANCE.w("12312312", model);
            return fakeMapper.transform(model);
        });
    }
}
