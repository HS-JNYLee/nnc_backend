package com.eastflag.nnc.testkmj.fcm;

import com.eastflag.nnc.testkmj.error.User1Exception;
import com.eastflag.nnc.testkmj.userrelation.UserRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.testkmj.error.ErrorCode.FCM_USER_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FcmService1 {
    private final FcmRepository1 fcmRepository;
    private final UserRelationService userRelationService;

    public Fcm1 createFcm(FcmRequest1 request) {
        var fcm = Fcm1.builder()
                .userId(request.getUserId())
                .fcmToken(request.getFcmToken())
                .build();

        fcmRepository.save(fcm);

        return fcm;
    }

    public Fcm1 createFcm(int userId, String fcmToken) {
        var fcm = Fcm1.builder()
                .userId(userId)
                .fcmToken(fcmToken)
                .build();

        fcmRepository.save(fcm);

        return fcm;
    }

    public Fcm1 updateFcm(FcmRequest1 request) {
        var fcm = fcmRepository
                .findByUserId(request.getUserId())
                .orElseThrow(() -> new User1Exception(FCM_USER_ID_NOT_FOUND));

        fcm.setFcmToken(request.getFcmToken());

        fcmRepository.save(fcm);

        return fcm;
    }
}
