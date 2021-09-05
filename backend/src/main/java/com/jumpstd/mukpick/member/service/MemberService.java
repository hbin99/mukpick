package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.member.dto.MemberDto;

import java.util.Map;

public interface MemberService {

    public int checkUserId(MemberDto memberDto);

    public Map<String,Object> register(MemberDto memberDto);

    public int update(MemberDto memberDto);

    public Map<String,Object> passwordFind(MemberDto memberDto);

    public Map<String,Object> userIdFind(MemberDto memberDto);

    public Map<String,Object> memberOutSend(MemberDto memberDto);

    public Map<String,Object> memberOut(MemberDto memberDto);
}
