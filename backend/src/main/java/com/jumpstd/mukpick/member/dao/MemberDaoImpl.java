package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.dto.SearchUserIdMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildAuthMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildMemberDto;
import com.jumpstd.mukpick.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MemberDaoImpl implements MemberDao{
    @Autowired
    private MemberMapper MemberMapper;

    @Override
    public int checkUser(SearchVaildMemberDto searchVaildMemberDto){
        return MemberMapper.checkUser(searchVaildMemberDto);
    }
    @Override
    public int register(MemberDto memberDto){
        return MemberMapper.register(memberDto);
    }
    @Override
    public int update(MemberDto memberDto){
        return MemberMapper.update(memberDto);
    }

    @Override
    public String userIdFind(SearchUserIdMemberDto searchUserIdMemberDto){
        return MemberMapper.userIdFind(searchUserIdMemberDto);
    }
    @Override
    public int userAuthCheck(SearchVaildAuthMemberDto searchVaildAuthMemberDto){
        return MemberMapper.userAuthCheck(searchVaildAuthMemberDto);
    }

    @Override
    public MemberDto findByUserData(String userId){
        return MemberMapper.findByUserData(userId);
    }

}
