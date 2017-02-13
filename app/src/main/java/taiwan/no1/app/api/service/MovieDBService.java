package taiwan.no1.app.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import taiwan.no1.app.data.entities.CastBriefEntity;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieBriefEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.SearchListResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;
import taiwan.no1.app.data.entities.TvBriefEntity;

/**
 * TmDB http api request interface set by using {@link retrofit2.Retrofit}.
 *
 * @author Jieyi
 * @since 12/29/16
 */

public interface MovieDBService {
    @GET("movie/popular")
    Observable<SearchListResEntity<MovieBriefEntity>> popularMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/top_rated")
    Observable<SearchListResEntity<MovieBriefEntity>> topRatedMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/now_playing")
    Observable<MovieListWithDateResEntity> nowPlayingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/upcoming")
    Observable<MovieListWithDateResEntity> upComingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/{id}")
    Observable<MovieDetailEntity> movieDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/{id}")
    Observable<CastDetailEntity> castDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("tv/airing_today")
    Observable<SearchListResEntity<TvBriefEntity>> airingTodayTvList(@QueryMap Map<String, String> queries);

    @GET("tv/on_the_air")
    Observable<SearchListResEntity<TvBriefEntity>> onTheAirTvList(@QueryMap Map<String, String> queries);

    @GET("tv/popular")
    Observable<SearchListResEntity<TvBriefEntity>> popularTvList(@QueryMap Map<String, String> queries);

    @GET("tv/top_rated")
    Observable<SearchListResEntity<TvBriefEntity>> topRatedTvList(@QueryMap Map<String, String> queries);

    @GET("tv/{id}")
    Observable<TVDetailEntity> tvDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/popular")
    Observable<SearchListResEntity<CastBriefEntity>> popularCastList(@QueryMap Map<String, String> queries);
}
