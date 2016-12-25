package taiwan.no1.app.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.FakeEntity;
import taiwan.no1.app.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    public Observable<FakeEntity> createEntity(@NonNull FakeModel model) {
        return null;
    }
}
