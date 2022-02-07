package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.*;
import com.jumpstd.mukpick.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDaoImpl implements MemberDao{
    @Autowired
    private MemberMapper MemberMapper;

   @Override
    public int findByUser(SearchVaildMemberDto searchVaildMemberDto){
        return MemberMapper.findByUser(searchVaildMemberDto);
    }
    @Override
    public int register(MemberRegisteDto memberRegisteDto){
        return MemberMapper.register(memberRegisteDto);
    }
    @Override
    public int update(MemberInfoDto memberInfoDto){
       return MemberMapper.update(memberInfoDto);
    }

    @Override
    public String findByUserId(SearchUserIdDto searchUserIdDto){
        return MemberMapper.findByUserId(searchUserIdDto);
    }
    @Override
    public int userAuthCheck(SearchVaildAuthMemberDto searchVaildAuthMemberDto){
        return MemberMapper.userAuthCheck(searchVaildAuthMemberDto);
    }

    @Override
    public MemberInfoDto findByUserData(String userId){
        return MemberMapper.findByUserData(userId);
    }


}
