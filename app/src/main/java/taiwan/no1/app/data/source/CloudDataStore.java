package taiwan.no1.app.data.source;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import taiwan.no1.app.R;
import taiwan.no1.app.api.service.MovieDBService;
import taiwan.no1.app.data.entities.ListResEntity;
import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.data.entities.cast.CastDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.data.entities.movie.MovieDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.search.SearchMovieEntity;
import taiwan.no1.app.data.entities.search.SearchTvShowsEntity;
import taiwan.no1.app.data.entities.tv.TvBriefEntity;
import taiwan.no1.app.data.entities.tv.TvDetailEntity;
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
    public Observable<ListResEntity<MovieBriefEntity>> moviesEntities(final Movies category, final int page) {
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
    public Observable<ListResEntity<TvBriefEntity>> TvsEntities(final Tvs category, final int page) {
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
    public Observable<TvDetailEntity> tvDetailEntities(final int id) {
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
    public Observable<ListResEntity<CastBriefEntity>> popularCastEntities(final int page) {
        Map<String, String> query = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("page", String.valueOf(page));
        }};

        return this.movieDBService.popularCastList(query);
    }

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-movies">Search-Movies</>
     */
    @Nullable
    @Override
    public Observable<SearchMovieEntity> searchMovieEntities(String language,
                                                             String query,
                                                             int page,
                                                             boolean include_adult,
                                                             String region,
                                                             int year,
                                                             int primary_release_year) {
        Map<String, String> q = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("language", language);
            put("query", query);
            put("page", String.valueOf(page));
            put("include_adult", String.valueOf(include_adult));
            put("region", region);
            put("year", String.valueOf(year));
            put("primary_release_year", String.valueOf(primary_release_year));
        }};

        return this.movieDBService.searchMovies(q);
    }

    /**
     * {@inheritDoc}
     *
     * @see <a href="https://developers.themoviedb.org/3/search/search-tv-shows">Search-TV-Shows</>
     */
    @Nullable
    @Override
    public Observable<SearchTvShowsEntity> searchTvShowsEntities(String language,
                                                                 String query,
                                                                 int page,
                                                                 int first_air_date_year) {
        Map<String, String> q = new ArrayMap<String, String>() {{
            put("api_key", api_key);
            put("language", language);
            put("query", query);
            put("page", String.valueOf(page));
            put("first_air_date_year", String.valueOf(first_air_date_year));
        }};

        return this.movieDBService.searchTvShows(q);
    }
}
