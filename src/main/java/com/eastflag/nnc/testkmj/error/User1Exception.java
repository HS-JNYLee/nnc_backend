package com.eastflag.nnc.testkmj.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User1Exception extends RuntimeException {
    private final ErrorCode errorCode;
}
