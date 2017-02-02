package taiwan.no1.app.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.CastListResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.MovieListResEntity;
import taiwan.no1.app.data.entities.MovieListWithDateResEntity;
import taiwan.no1.app.data.entities.TVDetailEntity;

/**
 * @author Jieyi
 * @since 12/29/16
 */

public interface MovieDBService {
    @GET("movie/popular")
    Observable<MovieListResEntity> popularMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/top_rated")
    Observable<MovieListResEntity> topRatedMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/now_playing")
    Observable<MovieListWithDateResEntity> nowPlayingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/upcoming")
    Observable<MovieListWithDateResEntity> upComingMovieList(@QueryMap Map<String, String> queries);

    @GET("movie/{id}")
    Observable<MovieDetailEntity> movieDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/{id}")
    Observable<CastDetailEntity> castDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("tv/{id}")
    Observable<TVDetailEntity> tvDetail(@Path("id") int id, @QueryMap Map<String, String> queries);

    @GET("person/popular")
    Observable<CastListResEntity> popularCastList(@QueryMap Map<String, String> queries);
}
