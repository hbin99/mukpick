package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.member.dto.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface MemberService {

    public Map<String,Object> checkUserId(SearchVaildMemberDto searchVaildMemberDto);

    public Map<String,Object> register(MemberDto memberDto);

    public Map<String,Object> update(MemberDto memberDto);

    public Map<String,Object> passwordFind(SearchVaildMemberDto searchVaildMemberDto);

    public Map<String,Object> userIdFind(SearchUserIdMemberDto searchUserIdMemberDto);

    public Map<String,Object> dropByUserMail(String user_id);

    public Map<String,Object> memberUpdateAuth(SearchVaildAuthMemberDto searchVaildAuthMemberDto);

    public Map<String,Object> login(LoginDto loginDto);
}
