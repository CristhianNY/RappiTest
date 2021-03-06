package com.cristhian.com.rappitest.api;

import com.cristhian.com.rappitest.model.MovieDetail;
import com.cristhian.com.rappitest.model.MovieResults;
import com.cristhian.com.rappitest.model.VideoMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(@Path("category") String category,
                                 @Query("api_key") String apiKey,
                                 @Query("language") String language,
                                 @Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") String MovieId,
                                     @Query("api_key") String apiKey,
                                     @Query("language") String language);

    @GET("/3/movie/{movie_id}/videos")
    Call<VideoMovie> getMovieVideo(@Path("movie_id") String MovieId,
                                   @Query("api_key") String apiKey,
                                   @Query("language") String language);

}
