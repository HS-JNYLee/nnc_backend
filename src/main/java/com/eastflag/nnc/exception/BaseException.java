package com.eastflag.nnc.exception;

import com.eastflag.nnc.exception.errorcode.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private final ErrorMessage errorCode;
}