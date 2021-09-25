package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.dto.SearchUserIdMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildAuthMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildMemberDto;

import java.util.Map;

public interface MemberService {

    public int checkUserId(SearchVaildMemberDto searchVaildMemberDto);

    public Map<String,Object> register(MemberDto memberDto);

    public int update(MemberDto memberDto);

    public Map<String,Object> passwordFind(SearchVaildMemberDto searchVaildMemberDto);

    public Map<String,Object> userIdFind(SearchUserIdMemberDto searchUserIdMemberDto);

    public Map<String,Object> dropByUserMail(String user_id);

    public Map<String,Object> memberUpdateAuth(SearchVaildAuthMemberDto searchVaildAuthMemberDto);
}
