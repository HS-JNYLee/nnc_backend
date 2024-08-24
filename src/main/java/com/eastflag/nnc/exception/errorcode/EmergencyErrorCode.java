package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface EmergencyErrorCode {
    ErrorMessage EMERGENCY_ID_NOT_FOUND = new ErrorMessage(404, "emergencyId가 존재하지 않습니다.");
    ErrorMessage EMERGENCY_USER_ID_NOT_FOUND = new ErrorMessage(404, "추가된 userId가 존재하지 않습니다.");
    ErrorMessage EMERGENCY_USER_ID_TELNUM_NOT_FOUND = new ErrorMessage(404, "추가된 userId, telNum이 없습니다.");
}
