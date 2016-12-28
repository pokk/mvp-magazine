package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.api.service.MovieDBService;
import taiwan.no1.app.data.entities.PopularResEntity;
import taiwan.no1.app.internal.di.components.NetComponent;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class CloudDataStore implements IDataStore {
    @Inject MovieDBService movieDBService;

    @Inject
    public CloudDataStore() {
        NetComponent.Initializer.init().inject(CloudDataStore.this);
    }

    @Nullable
    @Override
    public Observable<PopularResEntity> getPopularMovieEntities(int page) {
        Map<String, String> data = new HashMap<>();
        data.put("api_key", "987b940504b25045c8c8005c7c1ceab5");
        data.put("page", String.valueOf(1));

        return movieDBService.getPopularMovieList(data);
    }
}
