package com.eastflag.nnc.testkmj.error;

import com.eastflag.nnc.testkmj.error.errorcode.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private final ErrorMessage errorCode;
}