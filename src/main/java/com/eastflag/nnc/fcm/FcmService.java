package com.eastflag.nnc.fcm;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.google.api.core.ApiService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class FcmService {

    public CommonResponse postMessageCareGiver(MessageWrapper message) throws IOException {
        String token = getAccessToken();
        log.info(token);
        token = "Bearer " + token;
        postRetrofit(token, message);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(token)
                .build();
    }


    private static String getAccessToken() throws IOException {
        // Firebase 프로젝트 -> 서비스 계정 -> 새 비공개 키 생성
        String privateKeyFileName = "service-account.json";
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream(privateKeyFileName))
                .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredentials.refresh();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    private void postRetrofit(String token, MessageWrapper message) throws IOException {
        Response<ResponseBody> retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/v1/projects/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FcmInterface.class)
                .postCareGiver(token, message).execute();
    }
}
