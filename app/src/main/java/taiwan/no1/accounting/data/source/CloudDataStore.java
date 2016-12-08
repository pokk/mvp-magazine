package taiwan.no1.accounting.data.source;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class CloudDataStore implements IDataStore {
    @Inject
    public CloudDataStore() {
    }

    @Override
    public Observable<FakeEntity> createEntity(@NonNull FakeModel model) {
        return Observable.create(subscriber -> {
            subscriber.onNext(new FakeEntity("Test", 100, "F"));
            subscriber.onCompleted();
        });
    }
}
