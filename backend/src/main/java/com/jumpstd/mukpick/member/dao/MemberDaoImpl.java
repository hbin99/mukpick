package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDaoImpl implements MemberDao{
    @Autowired
    private MemberMapper MemberMapper;

    @Override
    public int checkUser(MemberDto memberDto){
        return MemberMapper.checkUser(memberDto);
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
    public int passwordReset(MemberDto memberDto){
        return MemberMapper.passwordReset(memberDto);
    }
    @Override
    public String userIdFind(MemberDto memberDto){
        return MemberMapper.userIdFind(memberDto);
    }

}
