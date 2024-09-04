package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface ScheduleErrorCode {
    ErrorMessage NO_SCHEDULE = new ErrorMessage(404, "조회된 스케줄이 없습니다.");
    ErrorMessage NO_SCHEDULE_IN_DATETIME = new ErrorMessage(404, "해당 날짜에 조회된 스케줄이 없습니다.");
    ErrorMessage SCHEDULE_ID_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 ScheduleId");
}
