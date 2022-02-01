package com.jumpstd.mukpick.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class MemberInfoDto {
    private String userId;
    private String password;
    private String userName;
    private String phone;
    private String profileImg;
    private String email;
    private char roleType;
    private char gender;
    private int age;
    private String authKey;

}

