package com.eastflag.nnc.exception.errorcode;

public interface FcmErrorCode {
    ErrorMessage FCM_USER_ID_NOT_FOUND = new ErrorMessage(404, "Fcm에 해당 userId가 존재하지 않습니다.");
}
