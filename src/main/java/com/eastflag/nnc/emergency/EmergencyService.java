package com.eastflag.nnc.emergency;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyService {
    @Autowired
    private final EmergencyRepository emergencyRepository;

    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    public Emergency getEmergencyByUserId(int id) {
        return emergencyRepository.findByUserId(id);
    }

    public CommonResponse addEmergency(Emergency emergency) {
        emergencyRepository.save(emergency);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(emergency)
                .build();
    }

    public CommonResponse deleteEmergency(Emergency emergency) {
        emergencyRepository.delete(emergency);
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
}
