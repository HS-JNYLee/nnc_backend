package com.eastflag.nnc.elevator;

import com.eastflag.nnc.common.ResponseMessage;
import org.springframework.stereotype.Component;
@Component
public class ElevatorResponseMessage implements ResponseMessage {
    String NOT_FOUND = "요청한 정보를 찾을 수 없습니다.";
}
