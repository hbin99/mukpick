package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.member.dto.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Optional;

public interface MemberService {

    int findByUser(String userId);

    int register(MemberRegisteDto memberRegisteDto);

    MemberInfoDto findByUserData(String userId);

    int update(MemberInfoDto memberInfoDto);

    int passwordFind(SearchVaildMemberDto searchVaildMemberDto);

    String findByUserId(SearchUserIdDto searchUserIdDto);

    int dropByUserMail(String userId);

    boolean memberUpdateAuth(SearchVaildAuthMemberDto searchVaildAuthMemberDto);

    //public MemberDto login(LoginDto loginDto);
}
