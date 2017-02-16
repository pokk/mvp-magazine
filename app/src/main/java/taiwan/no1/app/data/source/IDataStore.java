package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.ListResEntity;
import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.data.entities.cast.CastDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.data.entities.movie.MovieDetailEntity;
import taiwan.no1.app.data.entities.movie.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.search.SearchMovieEntity;
import taiwan.no1.app.data.entities.tv.TvBriefEntity;
import taiwan.no1.app.data.entities.tv.TvDetailEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author Jieyi
 * @since 12/6/16
 */

public interface IDataStore {
    /**
     * Retrieve a list of movie information by specific category, the response forms are difference between the below.
     *
     * @param category from {@link CloudDataStore.Movies}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link ListResEntity <MovieBriefEntity>}.
     */
    @Nullable
    Observable<ListResEntity<MovieBriefEntity>> moviesEntities(final CloudDataStore.Movies category, final int page);

    /**
     * Retrieve a list of movie information by specific category, the response forms are difference between the above.
     *
     * @param category from {@link CloudDataStore.Movies}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link MovieListWithDateResEntity}.
     */
    @Nullable
    Observable<MovieListWithDateResEntity> moviesWithDateEntities(final CloudDataStore.Movies category, final int page);

    /**
     * Retrieve a detail movie information by the movie id.
     *
     * @param id movie id.
     * @return an {@link rx.Observable} which will emit a {@link MovieDetailEntity}.
     */
    @Nullable
    Observable<MovieDetailEntity> movieDetailEntities(final int id);

    /**
     * Retrieve a detail cast information by the cast id.
     *
     * @param id cast id.
     * @return an {@link rx.Observable} which will emit a {@link CastDetailEntity}.
     */
    @Nullable
    Observable<CastDetailEntity> castDetailEntities(final int id);

    /**
     * Retrieve a list of tv information by specific category.
     *
     * @param category from {@link CloudDataStore.Tvs}.
     * @param page     a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link ListResEntity <TvBriefEntity>}.
     */
    @Nullable
    Observable<ListResEntity<TvBriefEntity>> TvsEntities(final CloudDataStore.Tvs category, final int page);

    /**
     * Retrieve a detail tv information by the tv id.
     *
     * @param id tv id.
     * @return an {@link rx.Observable} which will emit a {@link TvDetailEntity}.
     */
    @Nullable
    Observable<TvDetailEntity> tvDetailEntities(final int id);

    /**
     * Retrieve a list of the popular cast information.
     *
     * @param page a number of page from remote database.
     * @return an {@link rx.Observable} which will emit a {@link ListResEntity <CastBriefEntity>}.
     */
    @Nullable
    Observable<ListResEntity<CastBriefEntity>> popularCastEntities(final int page);

    /**
     * Search for movies.
     *
     * @param language Pass a ISO 639-1 value to display translated data for the fields that support it.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page Specify which page to query.
     * @param include_adult Choose whether to include adult (pornography) content in the results.
     * @param region Specify a ISO 3166-1 code to filter release dates.
     * @param year Release year.
     * @param primary_release_year Primary release year.
     * @return an {@link rx.Observable} which will emit a {@link SearchMovieEntity}.
     */
    @Nullable
    Observable<SearchMovieEntity> searchMovieEntities(String language,
                                                      String query,
                                                      int page,
                                                      boolean include_adult,
                                                      String region,
                                                      int year,
                                                      int primary_release_year);
}
