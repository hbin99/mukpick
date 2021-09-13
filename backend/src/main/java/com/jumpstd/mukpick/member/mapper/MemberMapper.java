package com.jumpstd.mukpick.member.mapper;

import com.jumpstd.mukpick.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository //해당 인터페이스가 저장소라는 것을 의미
public interface MemberMapper {
    public List<MemberDto> userList(MemberDto memberDto);

    public int checkUser(MemberDto memberDto);

    public int register(MemberDto memberDto);

    public int update(MemberDto memberDto);

    public int passwordReset(MemberDto memberDto);

    public String userIdFind(MemberDto memberDto);

    public int updateUSerRole(MemberDto memberDto);

    public int userAuthCheck(Map<String,Object> paramMap);
}
