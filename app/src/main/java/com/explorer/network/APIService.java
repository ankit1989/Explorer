package com.explorer.network;

import com.explorer.facts.model.FactResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ankitpatel on 3/4/18.
 */

public interface APIService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    @Headers(ApiUtils.CONTENT_TYPE_JSON)
    Call<FactResponse> getFacts();

}
