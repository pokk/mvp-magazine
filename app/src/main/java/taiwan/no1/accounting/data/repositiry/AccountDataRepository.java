package taiwan.no1.accounting.data.repositiry;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import rx.Observable;
import taiwan.no1.accounting.data.mapper.FakeEntityMapper;
import taiwan.no1.accounting.data.source.factory.DataStoreFactory;
import taiwan.no1.accounting.domain.repository.IAccountRepository;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * Low layer pure entity convert to kotlin layer data model from the repositories.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class AccountDataRepository implements IAccountRepository {
    private final DataStoreFactory dataStoreFactory;
    @Inject FakeEntityMapper fakeMapper;

    @Inject
    AccountDataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @Override
    @NonNull
    public Observable<FakeModel> CreateFakes(@NonNull final FakeModel fakeModel) {
        Preconditions.checkNotNull(fakeModel);

        return dataStoreFactory.createCloud()
                               .createEntity(fakeModel)
                               .map(entity -> fakeMapper.transformTo(entity));
    }
}
