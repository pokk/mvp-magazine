package taiwan.no1.accounting.data.source;

import android.support.annotation.NonNull;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @NonNull
    @Override
    public Observable<FakeEntity> createEntity(@NonNull FakeModel model) {
        return null;
    }
}
