package taiwan.no1.app.data.source;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.R;
import taiwan.no1.app.api.service.MovieDBService;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieCastsEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.internal.di.components.NetComponent;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class CloudDataStore implements IDataStore {
    @Inject MovieDBService movieDBService;

    private final Context context;
    private final String api_key;

    @Inject
    public CloudDataStore(Context context) {
        NetComponent.Initializer.init().inject(CloudDataStore.this);
        this.context = context;
        this.api_key = this.context.getString(R.string.moviedb_api_key);
    }

    /**
     * @param page page number.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/movies/get-popular-movies">Get-Popular-Movie</>
     */
    @Nullable
    @Override
    public Observable<MovieListResEntity> popularMovieEntities(final int page) {
        Map<String, String> query = new HashMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(1));
        }};

        return movieDBService.popularMovieList(query);
    }

    /**
     * @param id movie id.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/movies/get-movie-details">Get-Movie-Details</>
     */
    @Nullable
    @Override
    public Observable<MovieDetailEntity> movieDetailEntities(final int id) {
        Map<String, String> query = new HashMap<String, String>() {{
            put("api_key", api_key);
            put("append_to_response", "videos,images,similar,casts");
        }};

        return movieDBService.movieDetail(id, query);
    }

    /**
     * @param id movie id.
     * @return {@link Observable}
     */
    @Nullable
    @Override
    public Observable<MovieCastsEntity> movieCastsEntities(final int id) {
        Map<String, String> query = new HashMap<String, String>() {{
            put("api_key", api_key);
        }};

        return movieDBService.movieCastsDetail(id, query);
    }

    /**
     * @param id cast id.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/people">Get-Movie-Details</>
     */
    @Nullable
    @Override
    public Observable<CastDetailEntity> castDetailEntities(final int id) {
        Map<String, String> query = new HashMap<String, String>() {{
            put("api_key", api_key);
        }};

        return movieDBService.castDetail(id, query);
    }
}
