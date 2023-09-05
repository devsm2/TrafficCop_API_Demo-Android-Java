package com.trafficcop.apidemoapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

interface ApiService {
    @POST("https://tc.pubguru.net/v1")
    @Headers("Authorization: Token ADD_TOKEN_HERE")
    Call<TrafficCopResponse> checkIvtScore(@Body TrafficCopRequestBody trafficCopRequestBody);

}
