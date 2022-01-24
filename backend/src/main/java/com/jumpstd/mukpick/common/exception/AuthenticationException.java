package com.jumpstd.mukpick.common.exception;

public class AuthenticationException extends CustomException{
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
