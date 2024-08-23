package com.eastflag.nnc.exception;

import com.eastflag.nnc.exception.errorcode.ErrorMessage;
import com.eastflag.nnc.testkmj.error.errorcode.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private final ErrorMessage errorCode;
}