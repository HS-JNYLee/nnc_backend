package com.eastflag.nnc.testkmj.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ERROR_NOT_FOUND(404, "에러 원인 불명"),
    DATA_BASE_ERROR(404, "데이터베이스 접근 오류"),

    // User1
    USER_ID_NOT_FOUND(404, "존재하지 않는 UserId"),
    USER_ACCOUNT_NOT_FOUND(404, "존재하지 않는 UserAccount입니다."),
    CAREGIVER_IS_NOT_DELETE(202, "사용자와 관계 해제 후 삭제가 가능합니다."),

    // UserAccount
    USER_ACCOUNT_ID_NOT_FOUND(404, "존재하지 않는 UserAccountId입니다."),
    USER_ACCOUNT_EMAIL_NOT_FOUND(404, "존재하지 않는 UserAccountEmail입니다."),
    USER_ACCOUNT_EMAIL_AND_PASSWORD_NOT_FOUND(404, "Email과 Password에 부합하는 계정이 없습니다."),

    // UserSetting
    USER_SETTING_ID_NOT_FOUND(404, "존재하지 않는 UserSettingId입니다."),

    INTERNAL_SERVER_ERROR(500, "서버 에러"),

    // UserRelation
    RELATION_USER_ID_NOT_FOUND(404, "관계가 존재하지 않는 UserId입니다."),
    CARETAKER_ID_NOT_FOUND(404, "존재하지 않는 사용자Id입니다."),
    CAREGIVER_ID_NOT_FOUND(404, "존재하지 않는 보호자Id입니다."),

    // Fcm1
    FCM_USER_ID_NOT_FOUND(404, "Fcm에 해당 userId가 존재하지 않습니다.");

    private final int status;
    private final String message;
}
