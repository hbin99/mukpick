package com.jumpstd.mukpick.common.advice;

import com.jumpstd.mukpick.admin.exception.NoValidFoodNoException;
import com.jumpstd.mukpick.common.dto.ErrorResponse;
import com.jumpstd.mukpick.common.exception.AuthenticationException;
import com.jumpstd.mukpick.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleUnauthorized(CustomException e){
        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED)
                .message(e.getErrorCode().getMessage())
                .status(e.getErrorCode().getStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoValidFoodNoException.class)
    public ErrorResponse handleNoValidFoodNo(CustomException e){
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND)
                .message(e.getErrorCode().getMessage())
                .status(e.getErrorCode().getStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }


}
