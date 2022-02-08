package com.jumpstd.mukpick.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
public class MemberResponseDto {
    private String userId;
    private String password;
    private String userName;
    private String phone;
    private String email;
    private char roleType;
    private char gender;
    private int age;
    private Date registerDate;
}
