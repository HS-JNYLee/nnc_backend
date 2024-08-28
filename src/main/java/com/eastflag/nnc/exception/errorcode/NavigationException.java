package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface NavigationException {
    ErrorMessage CAREGIVER_ID_NOT_FOUND = new ErrorMessage(404, "Navigation에 일치하는 caregiverId가 없습니다.");
    ErrorMessage CARETAKER_ID_NOT_FOUND = new ErrorMessage(404, "Navigation에 일치하는 caretakerId가 없습니다.");
    ErrorMessage RELATION_ID_NOT_FOUND = new ErrorMessage(404, "Navigation에 일치하는 userId가 아닙니다.");
}
