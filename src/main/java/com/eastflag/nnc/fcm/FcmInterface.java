package com.eastflag.nnc.fcm;

import com.eastflag.nnc.fcm.request.MessageWrapper;
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
    @POST("338681595305/messages:send")
    Call<ResponseBody> postCareGiver(@Header("Authorization") String oAuthToken, @Body MessageWrapper message);
}
