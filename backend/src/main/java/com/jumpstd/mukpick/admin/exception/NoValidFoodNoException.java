package com.jumpstd.mukpick.admin.exception;

import com.jumpstd.mukpick.common.exception.CustomException;
import com.jumpstd.mukpick.common.exception.ErrorCode;

public class NoValidFoodNoException extends CustomException {
    public NoValidFoodNoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
