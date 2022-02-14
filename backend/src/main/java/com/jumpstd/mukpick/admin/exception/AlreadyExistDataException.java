package com.jumpstd.mukpick.admin.exception;

import com.jumpstd.mukpick.common.exception.CustomException;
import com.jumpstd.mukpick.common.exception.ErrorCode;

public class AlreadyExistDataException extends CustomException {
    public AlreadyExistDataException(ErrorCode errorCode) {
        super(errorCode);
    }
}
