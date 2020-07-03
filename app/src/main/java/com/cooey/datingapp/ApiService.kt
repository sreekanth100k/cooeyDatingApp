package com.cooey.datingapp;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {

    @GET("ceiNKFwyaa?indent=2")
    fun fetchApiResponse():Call<List<ApiResponse>>
}
