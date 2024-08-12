package com.eastflag.nnc.testkmj.fcm;

import com.eastflag.nnc.testkmj.userrelation.UserRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class FcmService {
    private final FcmRepository fcmRepository;
    private final UserRelationService userRelationService;

    public Fcm createFcm(FcmRequest request) {
        var fcm = Fcm.builder()
                .userId(request.getUserId())
                .fcmToken(request.getFcmToken())
                .build();

        fcmRepository.save(fcm);

        return fcm;
    }

    public Fcm updateFcm(FcmRequest request) {
        var fcm = fcmRepository
                .findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "의 토큰을 찾을 수 없음."));

        fcm.setFcmToken(request.getFcmToken());

        fcmRepository.save(fcm);

        return fcm;
    }

    private final static Logger LOG = Logger.getGlobal();
    public void anotherUserSendMessage(AnotherUserSendMessageRequest request){
        //TODO: 1. 상대 유저의 ID 및 토큰을 찾는다.
        /*var toUserId = userRelationService.getAnotherUserId(request.getUserId());
        var toUserFcmToken = fcmRepository
                .findByUserId(toUserId)
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "의 FCM토큰을 찾을 수 없음."));*/

        var toUserFcmToken = fcmRepository
                .findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "의 FCM토큰을 찾을 수 없음."));

        //TODO: 2. 전송
        LOG.info("FCM Message: anotherUserSendMessage \n " +
                //"userId: "+ toUserId + "\n" +
                "userId: "+ request.getUserId() + "\n" +
                "userFcmToken: " + toUserFcmToken + "\n" +
                "topic: "+ request.getTopic() + "\n" +
                "title: "+ request.getTitle() + "\n" +
                "body: "+request.getBody() + "\n");
    }

    public void SendMessage(SendMessage sendMessage){
        //TODO: 1. 상대 유저의 ID 및 토큰을 찾는다.
        var userRelation = userRelationService.getUserRelation(sendMessage.getUserId());
        var caretakerId = userRelation.getCaretakerId();
        var caregiverId = userRelation.getCaregiverId();

        var caretakerToken = fcmRepository
                .findByUserId(caretakerId)
                .orElseThrow(() -> new RuntimeException(caretakerId + "의 FCM토큰을 찾을 수 없음."));
        var caregiverToken = fcmRepository
                .findByUserId(caregiverId)
                .orElseThrow(() -> new RuntimeException(caregiverId + "의 FCM토큰을 찾을 수 없음."));
        //TODO: 2. 전송
        LOG.info("FCM Message: anotherUserSendMessage \n " +
                "userId: "+ caretakerId + ", " + caregiverId + "\n" +
                "userFcmToken: " + caretakerToken + ", " + caregiverToken + "\n" +
                "topic: "+ sendMessage.getTopic() + "\n" +
                "title: "+ sendMessage.getTitle() + "\n" +
                "body: "+sendMessage.getBody() + "\n");
    }
}
