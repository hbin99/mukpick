package com.jumpstd.mukpick.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    // METHOD NOT ALOOWED : 405
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "사용할 수 없는 메서드입니다."),

    // BAD REQUEST : 400

    // UNAUTHORIZED : 401
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED.value(), "로그인을 한 후 이용할 수 있습니다."),

    // FORBIDDEN : 403
    NO_ADMIN_USER(HttpStatus.FORBIDDEN.value(), "관리자만 이용하실 수 있습니다."),

    // NOT FOUND : 404
    NO_VALID_FOOD_NO(HttpStatus.NOT_FOUND.value(), "유효하지 않은 음식 번호입니다."),


    // SERVER ERROR : 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버에서 오류가 발생했습니다."),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY.value(), "다른 서버로부터 올바른 요청을 받지 못했습니다.")
    ;

    private final int status;
    private final String message;

    ErrorCode(int status, String message){
        this.status = status;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public int getStatus(){
        return this.status;
    }

}
