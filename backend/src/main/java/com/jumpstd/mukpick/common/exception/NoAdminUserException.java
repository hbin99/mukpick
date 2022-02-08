package com.jumpstd.mukpick.common.exception;

public class NoAdminUserException extends CustomException{
    public NoAdminUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
