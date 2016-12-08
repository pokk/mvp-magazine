package taiwan.no1.accounting.domain.repository;

import android.support.annotation.NonNull;

import rx.Observable;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * Interface that represents a Repository for getting {@link FakeModel} related data.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 5/29/16
 */

public interface AccountRepository {
    /**
     * Get an {@link Observable} which will emit a {@link FakeModel}.
     */
    Observable<FakeModel> CreateFakes(@NonNull FakeModel fakeModel);
}
