package com.eastflag.nnc.common;

import com.eastflag.nnc.exception.ErrorMessage;

public interface ResponseSuccess {
    ErrorMessage RESPONSE_SUCCESS = new ErrorMessage(200, "응답 성공: 응답이 성공적으로 완료되었습니다.");
    ErrorMessage RESPONSE_SUCCESS_REFUSE = new ErrorMessage(201, "응답 성공: 응답이 내부적으로 거절되었습니다.");
}
