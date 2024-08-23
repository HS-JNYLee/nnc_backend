package com.eastflag.nnc.exception.errorcode;

public interface User1ErrorCode {
    ErrorMessage USER_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 UserId");
    ErrorMessage USER_ACCOUNT_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 UserAccount입니다.");
    ErrorMessage CAREGIVER_IS_NOT_DELETE = new ErrorMessage(202, "사용자와 관계 해제 후 삭제가 가능합니다.");
    ErrorMessage NOT_CAREGIVER = new ErrorMessage(421, "이메일이 보호자 권한이 아닙니다.");
    ErrorMessage NO_TELNUM = new ErrorMessage(201, "일치하는 전화번호가 없습니다.");
    ErrorMessage NO_EMAIL = new ErrorMessage(201, "일치하는 이메일이 없습니다.");
}
