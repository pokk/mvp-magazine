package taiwan.no1.accounting.data.source.factory;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.accounting.data.source.IDataStore;

/**
 * Factory that creates different implementations of {@link IDataStore}.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
public class DataStoreFactory {
    private final IDataStore IDataStore;

    @Inject
    DataStoreFactory(IDataStore IDataStore) {
        this.IDataStore = IDataStore;
    }

    @NonNull
    public IDataStore create() {
        return this.IDataStore;
    }
}
