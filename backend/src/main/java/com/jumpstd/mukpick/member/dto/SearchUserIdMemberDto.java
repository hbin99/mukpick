package com.jumpstd.mukpick.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SearchUserIdMemberDto {
    private String email;
    private String userName;
    private String phone;


}

