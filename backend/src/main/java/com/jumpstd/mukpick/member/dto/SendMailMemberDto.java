package com.jumpstd.mukpick.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SendMailMemberDto {
    private String userId;
    private String flag;
    private String email;


}

