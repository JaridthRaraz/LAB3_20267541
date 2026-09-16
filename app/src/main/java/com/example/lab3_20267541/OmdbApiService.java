package com.example.lab3_20267541;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiService {
    @GET("/")
    Call<Pelicula> getPelicula(
            @Query("apikey") String apiKey,
            @Query("i") String imdbId
    );
}