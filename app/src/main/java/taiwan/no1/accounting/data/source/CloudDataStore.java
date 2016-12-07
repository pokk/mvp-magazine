package taiwan.no1.accounting.data.source;

import javax.inject.Inject;

import rx.Observable;
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
    public Observable<FakeModel> createEntity() {
        return Observable.create(subscriber -> {
            //            subscriber.onNext(null);
            subscriber.onCompleted();
        });
    }
}
