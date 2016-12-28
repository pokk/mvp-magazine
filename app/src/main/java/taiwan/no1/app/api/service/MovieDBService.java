package taiwan.no1.app.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import taiwan.no1.app.data.entities.PopularResEntity;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/29/16
 */

public interface MovieDBService {
    @GET("movie/popular")
    Observable<PopularResEntity> getPopularMovieList(@QueryMap Map<String, String> queries);
}
