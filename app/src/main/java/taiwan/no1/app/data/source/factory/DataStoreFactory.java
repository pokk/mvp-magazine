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
 * @author Jieyi
 * @since 12/6/16
 */

@Singleton
public class DataStoreFactory {
    private final Context context;

    @Inject
    public DataStoreFactory(Context context) {
        this.context = context;
    }

    /**
     * Create a local data object through repository pattern, ex. local database.
     *
     * @return a operation local data object.
     */
    @NonNull
    public IDataStore createLocal() {
        return new LocalDataStore();
    }

    /**
     * Create a remote data object through repository pattern.
     *
     * @return a operation remote data object.
     */
    @NonNull
    public IDataStore createCloud() {
        return new CloudDataStore(context);
    }
}
