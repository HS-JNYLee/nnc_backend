package com.eastflag.nnc.fcm;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Log4j2
@Service
public class FcmService {
    @Autowired
    private FcmRepository fcmRepository;
    public FcmService(FcmRepository fcmRepository) {
        this.fcmRepository = fcmRepository;
    }

    public CommonResponse postMessageCareGiver() throws IOException {
        String token = getAccessToken();
        log.info(token);
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

    private static void postGson() {

    }
}
