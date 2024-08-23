package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface BasicErrorCode {
    ErrorMessage ERROR_NOT_FOUND = new ErrorMessage(404, "에러 원인 불명");
    ErrorMessage DATA_BASE_ERROR = new ErrorMessage(404, "데이터베이스 접근 오류");
    ErrorMessage INTERNAL_SERVER_ERROR = new ErrorMessage(500, "서버 에러");
    ErrorMessage UNSUPPORTED_ENCODING = new ErrorMessage(415, "잘못된 형식으로 인코딩이 되었습니다.");
}