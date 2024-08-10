package com.eastflag.nnc.emergency;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyService {
    @Autowired
    private final EmergencyRepository emergencyRepository;

    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    public CommonResponse getEmergencyByEmergencyId(int emergencyId) {
        Emergency emergency = emergencyRepository.findByEmergencyId(emergencyId);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    public CommonResponse addEmergency(Emergency emergency) {
        emergencyRepository.save(emergency);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    public CommonResponse deleteEmergency(int emergencyId) {
        Emergency emergency = emergencyRepository.deleteByEmergencyId(emergencyId);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
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

    public CommonResponse getAllEmergency(Integer userId) {
        List<Emergency> emergencies = emergencyRepository.findByUserId(userId);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergencies)
                .build();
    }

    public CommonResponse getEmergencyByUserIdAndTelNum(int userId, String telNum) {
        Optional<Emergency> emergency = emergencyRepository.findByUserIdAndTelNum(userId, telNum);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }
}
