package com.mptechprojects.lab3_20193733;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrimeNumberApiService {

    @GET("/primeNumbers")
    Call<List<PrimeNumber>> getPrimeNumbers(@Query("len") int length, @Query("order") int order);



}
