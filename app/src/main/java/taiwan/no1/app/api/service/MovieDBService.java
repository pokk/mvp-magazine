package taiwan.no1.app.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
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
 * TmDB http api request interface set by using {@link retrofit2.Retrofit}.
 *
 * @author Jieyi
 * @since 12/29/16
 */

public interface MovieDBService {
    @GET("movie/popular")
    Observable<ListResEntity<MovieBriefEntity>> popularMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/top_rated")
    Observable<ListResEntity<MovieBriefEntity>> topRatedMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/now_playing")
    Observable<MovieListWithDateResEntity> nowPlayingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/upcoming")
    Observable<MovieListWithDateResEntity> upComingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/{id}")
    Observable<MovieDetailEntity> movieDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/{id}")
    Observable<CastDetailEntity> castDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("tv/airing_today")
    Observable<ListResEntity<TvBriefEntity>> airingTodayTvList(@QueryMap Map<String, String> queries);

    @GET("tv/on_the_air")
    Observable<ListResEntity<TvBriefEntity>> onTheAirTvList(@QueryMap Map<String, String> queries);

    @GET("tv/popular")
    Observable<ListResEntity<TvBriefEntity>> popularTvList(@QueryMap Map<String, String> queries);

    @GET("tv/top_rated")
    Observable<ListResEntity<TvBriefEntity>> topRatedTvList(@QueryMap Map<String, String> queries);

    @GET("tv/{id}")
    Observable<TvDetailEntity> tvDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/popular")
    Observable<ListResEntity<CastBriefEntity>> popularCastList(@QueryMap Map<String, String> queries);

    @GET("search/movie")
    Observable<SearchMovieEntity> searchMovie(@QueryMap Map<String, String> queries);
}
