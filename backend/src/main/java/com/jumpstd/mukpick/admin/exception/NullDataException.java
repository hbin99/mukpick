package com.jumpstd.mukpick.admin.exception;

import com.jumpstd.mukpick.common.exception.CustomException;
import com.jumpstd.mukpick.common.exception.ErrorCode;

public class NullDataException extends CustomException {
    public NullDataException(ErrorCode errorCode) {
        super(errorCode);
    }

}
