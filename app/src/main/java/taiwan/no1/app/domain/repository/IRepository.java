package taiwan.no1.app.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import taiwan.no1.app.data.source.CloudDataStore;
import taiwan.no1.app.mvp.models.cast.CastDetailModel;
import taiwan.no1.app.mvp.models.cast.CastListResModel;
import taiwan.no1.app.mvp.models.movie.MovieBriefModel;
import taiwan.no1.app.mvp.models.movie.MovieDetailModel;
import taiwan.no1.app.mvp.models.search.SearchMovieModel;
import taiwan.no1.app.mvp.models.search.SearchTvShowsModel;
import taiwan.no1.app.mvp.models.tv.TvBriefModel;
import taiwan.no1.app.mvp.models.tv.TvDetailModel;
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel;
import taiwan.no1.app.mvp.models.tv.TvListResModel;
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel;

/**
 * Interface that represents a Repository for getting a movie related data.
 *
 * @author Jieyi
 * @since 5/29/16
 */

public interface IRepository {
    /**
     * Get an {@link Observable} which will emit a {@link List<MovieBriefModel>}.
     *
     * @param page a page of a movie list.
     * @return {@link Observable}
     */
    @NonNull
    Observable<List<MovieBriefModel>> movies(final CloudDataStore.Movies category, final int page);

    /**
     * Get an {@link Observable} which will emit a {@link MovieDetailModel}.
     *
     * @param id a movie id.
     * @return {@link Observable}
     */
    @NonNull
    Observable<MovieDetailModel> detailMovie(final int id);

    /**
     * Get an {@link Observable} which will emit a {@link CastDetailModel}.
     *
     * @param id a cast id.
     * @return {@link Observable}
     */
    @NonNull
    Observable<CastDetailModel> castDetail(final int id);

    /**
     * Get an {@link Observable} which will emit a {@link TvListResModel}.
     *
     * @param page a page of a tv list.
     * @return {@link Observable}
     */
    @NonNull
    Observable<List<TvBriefModel>> tvs(final CloudDataStore.Tvs category, final int page);

    /**
     * Get an {@link Observable} which will emit a {@link TvDetailModel}.
     *
     * @param id a tv id.
     * @return {@link Observable}
     */
    @NonNull
    Observable<TvDetailModel> detailTv(final int id);

    /**
     * Get an {@link Observable} which will emit a {@link TvSeasonsModel}.
     *
     * @param id           a tv id.
     * @param seasonNumber the season number of a tv.
     * @return {@link Observable}
     */
    @NonNull
    Observable<TvSeasonsModel> detailTvSeason(final int id, final int seasonNumber);

    /**
     * Get an {@link Observable} which will emit a {@link TvEpisodesModel}.
     *
     * @param id            a tv id.
     * @param seasonNumber  the season number of a tv.
     * @param episodeNumber the episode number of a season number of a tv.
     * @return {@link Observable}
     */
    @NonNull
    Observable<TvEpisodesModel> detailTvEpisode(final int id, final int seasonNumber, final int episodeNumber);

    /**
     * Get an {@link Observable} which will emit a {@link List<CastListResModel>}.
     *
     * @param page a page of popular cast list.
     * @return {@link Observable}
     */
    @NonNull
    Observable<CastListResModel> casts(final int page);

    /**
     * Get an {@link Observable} which will emit a {@link List<SearchMovieModel>}.
     *
     * @param language             Pass a ISO 639-1 value to display translated data for the fields that support it.
     * @param query                Pass a text query to search. This value should be URI encoded.
     * @param page                 Specify which page to query.
     * @param include_adult        Choose whether to include adult (pornography) content in the results.
     * @param region               Specify a ISO 3166-1 code to filter release dates.
     * @param year                 Release year.
     * @param primary_release_year Primary release year.
     * @return {@link Observable}
     */
    @NonNull
    Observable<SearchMovieModel> searchMovies(final String language, final String query, final int page,
                                              final boolean include_adult, final String region, final int year,
                                              final int primary_release_year);

    /**
     * Get an {@link Observable} which will emit a {@link List<SearchTvShowsModel>}.
     *
     * @param language            Pass a ISO 639-1 value to display translated data for the fields that support it.
     * @param query               Pass a text query to search. This value should be URI encoded.
     * @param page                Specify which page to query.
     * @param first_air_date_year Specify first air date or year.
     * @return {@link Observable}
     */
    @NonNull
    Observable<SearchTvShowsModel> searchTvShows(final String language, final String query, final int page,
                                                 final int first_air_date_year);
}
