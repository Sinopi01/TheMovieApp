package com.example.sinopi.Network;

import com.example.sinopi.Models.MovieModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sinopi on 4/15/17.
 */

public interface NetworkRequest {

        @GET("/3/movie/popular")
        Call<MovieModels.SearchResult> getPopularMovies(@Query(value = "api_key") String query);

        @GET("/3/movie/top_rated")
        Call<MovieModels.SearchResult> getTopRatedMovies(@Query(value = "api_key")String query);
}
