package taiwan.no1.accounting.data.source.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.accounting.data.source.IDataStore;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
public class DataStoreFactory {
    private taiwan.no1.accounting.data.source.IDataStore IDataStore;

    @Inject
    DataStoreFactory(IDataStore IDataStore) {
        this.IDataStore = IDataStore;
    }

    public IDataStore create() {
        return this.IDataStore;
    }
}
