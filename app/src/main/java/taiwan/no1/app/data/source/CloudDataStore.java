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
import taiwan.no1.app.data.entities.TvListResEntity;
import taiwan.no1.app.internal.di.components.NetComponent;

/**
 * The http api sets of retrieving the movies, casts, tvs information
 *
 * @author Jieyi
 * @since 12/6/16
 */

public class CloudDataStore implements IDataStore {
    @Inject MovieDBService movieDBService;

    private final Context context;
    private final String api_key;

    // Categories of TmDB movie list.
    public enum Movies {
        POPULAR,
        TOP_RATED,
        NOW_PLAYING,
        UP_COMING,
    }

    // Categories of TmDB tv list.
    public enum Tvs {
        ON_THE_AIR,
        AIRING_TODAY,
        POPULAR,
        TOP_RATED,
    }

    @Inject
    public CloudDataStore(Context context) {
        NetComponent.Initializer.init().inject(CloudDataStore.this);
        this.context = context;
        this.api_key = this.context.getString(R.string.moviedb_api_key);
    }

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/movies/get-popular-movies">Get-Popular-Movie</>
     * @see <a href="https://developers.themoviedb.org/3/movies/get-top-rated-movies">Get-Top-Rated-Movie</>
     */
    @Nullable
    @Override
    public Observable<MovieListResEntity> moviesEntities(final Movies category, final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        switch (category) {
            case POPULAR:
                return this.movieDBService.popularMovieList(query);
            case TOP_RATED:
                return this.movieDBService.topRatedMovieList(query);
        }

        throw new Error("Movies doesn't have this type!");
    }

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/movies/get-now-playing">Get-Now-Playing-Movie</>
     * @see <a href="https://developers.themoviedb.org/3/movies/get-upcoming">Get-Upcoming-Movie</>
     */
    @Nullable
    @Override
    public Observable<MovieListWithDateResEntity> moviesWithDateEntities(final Movies category, final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        switch (category) {
            case NOW_PLAYING:
                return this.movieDBService.nowPlayingMovieList(query);
            case UP_COMING:
                return this.movieDBService.upComingMovieList(query);
        }

        throw new Error("Movies doesn't have this type!");
    }

    /**
     * {@inheritDoc}
     *
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
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/people/get-person-details">Get-Person-Details</>
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

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/tv/get-tv-airing-today">Get-Tv-Airing-Today</>
     * @see <a href="https://developers.themoviedb.org/3/tv/get-tv-on-the-air">Get-On-The-Air</>
     * @see <a href="https://developers.themoviedb.org/3/tv/get-popular-tv-shows">Get-Popular-Tv-Shows</>
     * @see <a href="https://developers.themoviedb.org/3/tv/get-top-rated-tv">Get-Top-Rated-Tv</>
     */
    @Nullable
    @Override
    public Observable<TvListResEntity> TvsEntities(final Tvs category, final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        switch (category) {
            case ON_THE_AIR:
                return this.movieDBService.onTheAirTvList(query);
            case AIRING_TODAY:
                return this.movieDBService.airingTodayTvList(query);
            case POPULAR:
                return this.movieDBService.popularTvList(query);
            case TOP_RATED:
                return this.movieDBService.topRatedTvList(query);
        }

        throw new Error("Tvs doesn't have this type!");
    }

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/tv/get-tv-details">Get-Tv-Details</>
     */
    @Nullable
    @Override
    public Observable<TVDetailEntity> tvDetailEntities(final int id) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("append_to_response", "videos,images,similar,credits");
        }};

        return this.movieDBService.tvDetail(id, query);
    }

    /**
     * {@inheritDoc}
     *
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
