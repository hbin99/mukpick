package com.jumpstd.mukpick.member.exception;

import com.jumpstd.mukpick.common.exception.CustomException;
import com.jumpstd.mukpick.common.exception.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
