package com.cooey.datingapp;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {

    @GET("json/get/ceiNKFwyaa?indent=2")
    fun fetchApiResponse():Call<ApiResponse>
}
