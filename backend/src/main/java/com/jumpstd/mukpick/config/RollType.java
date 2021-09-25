package com.jumpstd.mukpick.config;

public enum RollType {
    // 1 : 시스템관리자
    // 2 : 일반유저
    // 3 : 탈퇴유저
    // 4 : 정지유저
    // 5 : 회원가입 전단계 (이메일 확인 후 role_type 2로 변경)
    ADMIN('1'),
    USER('2'),
    DROP_USER('3'),
    STOP_USER('4'),
    BEFORE_SING_UP_USER('5');

    private final char value;

    RollType(char i) {
        this.value = i;
    }

    public char getValue(){
        return value;
    }
}
