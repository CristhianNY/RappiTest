package com.cristhian.com.rappitest.domain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppiInterface  {

@GET("/3/movie/{category}")

    Call<MovieResults> getMovies(@Path("category") String category,
                                 @Path("api_key") String apiKey,
                                 @Path("language") String language,
                                 @Path("page") String page);

}
