package taiwan.no1.accounting.data.source;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.internal.Preconditions;
import retrofit2.Retrofit;
import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.internal.di.components.NetComponent;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class CloudDataStore implements IDataStore {
    @Inject @Named("FakeHttp") Retrofit retrofit;

    @Inject
    public CloudDataStore() {
        NetComponent.Initializer.INSTANCE.init().inject(CloudDataStore.this);
    }

    @NonNull
    @Override
    public Observable<FakeEntity> createEntity(@NonNull final FakeModel model) {
        Preconditions.checkNotNull(model);

        return Observable.create(subscriber -> {
            subscriber.onNext(new FakeEntity("Test", 100, "F"));
            subscriber.onCompleted();
        });
    }
}
