package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.*;

import java.util.Map;

public interface MemberDao {

    int findByUser(SearchVaildMemberDto searchVaildMemberDto);
    int register(MemberRegisteDto memberRegisteDto);
    int update(MemberInfoDto memberInfoDto);

    String findByUserId(SearchUserIdDto searchUserIdDto);

    int userAuthCheck(SearchVaildAuthMemberDto searchVaildAuthMemberDto);

    MemberInfoDto findByUserData(String userId);


}
