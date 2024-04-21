package com.mptechprojects.lab3_20193733;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmApiService {

    @GET("/")
    Call<Film> getFilms(@Query("apikey") String apikey,@Query("i") String i);

}
