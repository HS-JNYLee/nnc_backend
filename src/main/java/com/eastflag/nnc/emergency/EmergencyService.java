package com.eastflag.nnc.emergency;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.exception.ControlledException;
import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.GitHubAvatar;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.eastflag.nnc.exception.errorcode.EmergencyErrorCode.*;

@Service
public class EmergencyService {
    @Autowired
    private final EmergencyRepository emergencyRepository;

    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }
    
    public CommonResponse getEmergencyByEmergencyId(int emergencyId) {
        Emergency emergency = emergencyRepository
                .findByEmergencyId(emergencyId)
                .orElseThrow(() -> new ControlledException(EMERGENCY_ID_NOT_FOUND));
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    public CommonResponse addEmergency(Emergency emergency) {
        /**
         *  깃헙 프로필 아이콘 아바타 만들기
         */
        Avatar avatar = GitHubAvatar.newAvatarBuilder().build();
        Random random = new Random();
        long randomLong = random.nextLong();
        emergency.setFileData(avatar.createAsPngBytes(randomLong));
        var returnEmergency = emergencyRepository.save(emergency);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(returnEmergency)
                .build();
    }

    public CommonResponse deleteEmergency(int emergencyId) {
        Integer index = emergencyRepository
                .deleteByEmergencyId(emergencyId)
                .orElseThrow(() -> new ControlledException(EMERGENCY_ID_NOT_FOUND));
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(index)
                .build();
    }

    @Transactional
    public CommonResponse updateEmergency(Emergency emergency) {
        Optional<Emergency> emergency1 = emergencyRepository.findByUserIdAndTelNum(emergency.getUserId(), emergency.getTelNum());
        emergency1.ifPresent(value -> emergencyRepository.save(emergency));
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    // 북마크 등록하는 로직
    public CommonResponse updateEmergencyByEmergencyId(int emergencyId) {
        Emergency emergency = emergencyRepository.findByEmergencyId(emergencyId)
                .orElseThrow(() -> new ControlledException(EMERGENCY_ID_NOT_FOUND));
        emergency.setBookmarkYn("Y"); // 북마크
        emergencyRepository.save(emergency);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    public CommonResponse getAllEmergency(Integer userId) {
        List<Emergency> emergencies = emergencyRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ControlledException(EMERGENCY_USER_ID_NOT_FOUND));
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergencies)
                .build();
    }

    public CommonResponse getAllEmergencyByBookmark(Integer userId, String bookmarkYn) {
        List<Emergency> emergencies = emergencyRepository
                .findByUserIdAndBookmarkYn(userId, bookmarkYn)
                .orElseThrow(() -> new ControlledException(EMERGENCY_USER_ID_NOT_FOUND));
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergencies)
                .build();
    }

    public CommonResponse getEmergencyByUserIdAndTelNum(int userId, String telNum) {
        Emergency emergency = emergencyRepository
                .findByUserIdAndTelNum(userId, telNum)
                .orElseThrow(() -> new ControlledException(EMERGENCY_USER_ID_TELNUM_NOT_FOUND));

        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }
}
