package com.jumpstd.mukpick.admin.domain;

import com.jumpstd.mukpick.admin.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
public class MemberMntDomain {
    private String userId;
    private String password;
    private String userName;
    private String phone;
    private String email;
    private char roleType;
    private char gender;
    private int age;
    private Date registerDate;

    public MemberResponseDto getMemberMntDto() {
        return new MemberResponseDto(
                userId,
                password,
                userName,
                phone,
                email,
                roleType,
                gender,
                age,
                registerDate
        );
    }
}
