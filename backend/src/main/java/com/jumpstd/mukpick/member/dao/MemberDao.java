package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.dto.SearchUserIdMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildAuthMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildMemberDto;

import java.util.Map;

public interface MemberDao {

    public int checkUser(SearchVaildMemberDto searchVaildMemberDto);
    public int register(MemberDto memberDto);
    public int update(MemberDto memberDto);
    public String userIdFind(SearchUserIdMemberDto searchUserIdMemberDto);
    public int userAuthCheck(SearchVaildAuthMemberDto searchVaildAuthMemberDto);
    public MemberDto findByUserData(String userId);

}
