package taiwan.no1.app.data.source.factory;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.source.CloudDataStore;
import taiwan.no1.app.data.source.IDataStore;
import taiwan.no1.app.data.source.LocalDataStore;

/**
 * Factory that creates different implementations of {@link IDataStore}.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
public class DataStoreFactory {
    private final Context context;

    @Inject
    DataStoreFactory(Context context) {
        this.context = context;
    }

    @NonNull
    public IDataStore createLocal() {
        return new LocalDataStore();
    }

    @NonNull
    public IDataStore createCloud() {
        return new CloudDataStore(context);
    }
}
