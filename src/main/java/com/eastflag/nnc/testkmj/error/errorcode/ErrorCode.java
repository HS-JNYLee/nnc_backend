package com.eastflag.nnc.testkmj.error.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorCode {
    private final int status;
    private final String message;
}