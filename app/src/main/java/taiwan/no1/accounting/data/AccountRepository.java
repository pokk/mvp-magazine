package taiwan.no1.accounting.data;

import android.support.annotation.NonNull;

import rx.Observable;
import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * Created by lekaha on 5/29/16.
 */
public interface AccountRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link FakeEntity}.
     */
    Observable<FakeEntity> CreateFakes(@NonNull FakeModel fakeModel);
}
