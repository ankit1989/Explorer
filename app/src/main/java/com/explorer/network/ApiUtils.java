package com.explorer.network;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://dl.dropboxusercontent.com";
    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
