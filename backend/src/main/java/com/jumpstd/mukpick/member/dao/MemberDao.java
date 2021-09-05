package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.dto.MemberDto;

public interface MemberDao {

    public int checkUser(MemberDto memberDto);
    public int register(MemberDto memberDto);
    public int update(MemberDto memberDto);
    public int passwordReset(MemberDto memberDto);
    public String userIdFind(MemberDto memberDto);

}
