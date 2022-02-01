package com.jumpstd.mukpick.member.mapper;

import com.jumpstd.mukpick.member.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //해당 인터페이스가 저장소라는 것을 의미
public interface MemberMapper {

    int findByUser(SearchVaildMemberDto searchVaildMemberDto);

    int register(MemberRegisteDto memberRegisteDto);

    int update(MemberInfoDto memberInfoDto);

    String findByUserId(SearchUserIdDto searchUserIdDto);

    int userAuthCheck(SearchVaildAuthMemberDto searchVaildAuthMemberDto);

    MemberInfoDto findByUserData(String userId);


}
