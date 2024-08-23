package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface UserSettingErrorCode {
    ErrorMessage USER_SETTING_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 UserSettingId입니다.");
}
