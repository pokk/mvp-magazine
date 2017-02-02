package taiwan.no1.app.data.source;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.R;
import taiwan.no1.app.api.service.MovieDBService;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.CastListResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;
import taiwan.no1.app.internal.di.components.NetComponent;

/**
 * @author Jieyi
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
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.popularMovieList(query);
    }

    @Nullable
    @Override
    public Observable<MovieListResEntity> topRatedMovieEntities(final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.topRatedMovieList(query);
    }

    @Nullable
    @Override
    public Observable<MovieListWithDateResEntity> nowPlayingMovieEntities(final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.nowPlayingMovieList(query);
    }

    @Nullable
    @Override
    public Observable<MovieListWithDateResEntity> upComingMovieEntities(final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.upComingMovieList(query);
    }

    /**
     * @param id movie id.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/movies/get-movie-details">Get-Movie-Details</>
     */
    @Nullable
    @Override
    public Observable<MovieDetailEntity> movieDetailEntities(final int id) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("append_to_response", "videos,images,similar,casts");
        }};

        return this.movieDBService.movieDetail(id, query);
    }

    /**
     * @param id cast id.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/people">Get-Movie-Details</>
     */
    @Nullable
    @Override
    public Observable<CastDetailEntity> castDetailEntities(final int id) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("append_to_response", "images,combined_credits");
        }};

        return this.movieDBService.castDetail(id, query);
    }

    @Nullable
    @Override
    public Observable<TVDetailEntity> tvDetailEntities(final int id) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("append_to_response", "videos, images, similar, casts");
        }};

        return this.movieDBService.tvDetail(id, query);
    }

    /**
     * @param page page number.
     * @return {@link Observable}
     * @see <a href="https://developers.themoviedb.org/3/people/get-popular-people">Get-Popular-Casts</>
     */
    @Nullable
    @Override
    public Observable<CastListResEntity> popularCastEntities(final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.popularCastList(query);
    }
}
