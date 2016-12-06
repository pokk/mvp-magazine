package taiwan.no1.accounting.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.accounting.data.source.DataStore;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
class DataStoreFactory {
    private DataStore dataStore;

    @Inject
    DataStoreFactory(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public DataStore create() {
        return this.dataStore;
    }
}
