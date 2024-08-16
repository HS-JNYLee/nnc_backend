package com.eastflag.nnc.testkmj.error.errorcode;

public interface UserAccountErrorCode {
    ErrorMessage USER_ACCOUNT_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 UserAccountId입니다.");
    ErrorMessage USER_ACCOUNT_EMAIL_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 UserAccountEmail입니다.");
    ErrorMessage USER_ACCOUNT_EMAIL_AND_PASSWORD_NOT_FOUND = new ErrorMessage(404, "Email과 Password에 부합하는 계정이 없습니다.");
}
