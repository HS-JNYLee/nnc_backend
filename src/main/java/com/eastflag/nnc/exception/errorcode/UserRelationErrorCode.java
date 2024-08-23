package com.eastflag.nnc.exception.errorcode;

public interface UserRelationErrorCode {
    ErrorMessage RELATION_USER_ID_NOT_FOUND = new ErrorMessage(404, "관계가 존재하지 않는 UserId입니다.");
    ErrorMessage CARETAKER_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 사용자Id입니다.");
    ErrorMessage CAREGIVER_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 보호자Id입니다.");
}
