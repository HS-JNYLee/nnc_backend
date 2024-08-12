package com.eastflag.nnc.fcm;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FcmInterface {

    @Headers({
            "accept: application/json",
            "content-type: application/json"
    })
    @POST("657423783174/messages:send")
    Call<ResponseBody> postCareGiver(@Header("Authorization") String oAuthToken, @Body MessageWrapper message);
}
