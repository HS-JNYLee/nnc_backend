package com.eastflag.nnc.exception.errorcode;

import com.eastflag.nnc.exception.ErrorMessage;

public interface ElevatorErrorCode {
    ErrorMessage SUBWAY_NAME_NOT_EXIST = new ErrorMessage(404, "찾는 지하철 역이 없음");
    ErrorMessage ELEVATOR_ID_NOT_EXIST = new ErrorMessage(405, "찾는 엘리베이터 아이디가 존재하지 않음");
}