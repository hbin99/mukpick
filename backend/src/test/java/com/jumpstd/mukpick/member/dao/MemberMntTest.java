package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.config.RollType;
import com.jumpstd.mukpick.mail.service.MailService;
import com.jumpstd.mukpick.member.dto.*;
import com.jumpstd.mukpick.member.mapper.MemberMapper;
import com.jumpstd.mukpick.member.service.MemberService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
@DisplayName("Test")
class MemberMntTest {
    @Autowired
    MemberMapper mapper;

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;

   /* @BeforeEach
    @DisplayName("Member insert")
    public void param(){
        MemberRegisteDto dto = new MemberRegisteDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("hyebin9612@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-6666");
        dto.setGender('F');
        dto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());
        dto.setUserName("테스트2");

        mapper.register(dto);
        MemberInfoDto memberInfoDto = memberService.findByUserData(dto.getUserId());
        System.out.println(memberInfoDto.toString());
    }*/

    @Test
    @DisplayName("id check")
    public void findByUser(){
        String user_id ="test";
        int result = memberService.findByUser(user_id);
        assertThat(result).isEqualTo(1);
    }
    @Test
    @DisplayName("register > mail send ")
    public void registerSendMail(){
        MemberRegisteDto dto = new MemberRegisteDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("hyebin9612@gmail.com");
        dto.setPassword("dbseoyyyy");
        dto.setProfileImg("");
        dto.setPhone("010-3333-2222");
        dto.setGender('F');
        dto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());
        dto.setUserName("테스트");

        int result = memberService.register(dto);

        assertThat(result).isEqualTo(1);
        MemberInfoDto memberInfoDto = memberService.findByUserData(dto.getUserId());
        System.out.println(">>>>>>>>> "+memberInfoDto.toString());
    }

    @Test
    @DisplayName("register mail click ")
    public void register() {
        // given
        MemberInfoDto dto = new MemberInfoDto();
        dto.setUserId("test2");
        dto.setRoleType(RollType.USER.getValue());
        dto.setAuthKey("");
        // when
        memberService.update(dto);
        // then
        int result = memberService.findByUser(dto.getUserId());
        System.out.println(">>>>>>>>> "+result);

    }
    @Test
    @DisplayName("id find")
    public void userIdFind() {
        // given
        SearchUserIdDto dto = new SearchUserIdDto();
        dto.setUserName("테스트");
        dto.setEmail("hyebin9612@gmail.com");
        dto.setPhone("010-1111-2222");
        // when
        String result= memberService.findByUserId(dto);
        // then
        assertThat(result).isEqualTo(result);
    }

    @Test
    @DisplayName("password find")
    public void passwordFind() {
        // given
        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId("test2");
        searchVaildMemberDto.setEmail("hyebin9612@gmail.com");
        // when
        int result = memberService.passwordFind(searchVaildMemberDto);
        // then
        System.out.println(">>>>>>>>> "+result);
        assertThat(result).isEqualTo(1);
    }
    @Test
    @DisplayName("member Update")
    public void update() {
        // given
        MemberInfoDto dto = new MemberInfoDto();
        dto.setUserId("test");
        //dto.setEmail("hyebin9612@gmail.com");
        //dto.setPhone("010-1234-5678");
        dto.setAge(13);
        // when
        memberService.update(dto);
        // then
        MemberInfoDto memberInfoDto = memberService.findByUserData(dto.getUserId());
        System.out.println(">>>>>>>>> "+memberInfoDto.toString());

    }
    @Test
    @DisplayName("drop member > mail send")
    public void memberOutSend() {
        // given
        String user_id = "test";
        // when
        int result = memberService.dropByUserMail(user_id);
        // then
        assertThat(result).isEqualTo(1);
    }
   /* @Test
    @DisplayName("drop member")
    public void memberOut() {
        // given
        SearchVaildAuthMemberDto searchVaildAuthMemberDto = new SearchVaildAuthMemberDto();
        searchVaildAuthMemberDto.setUserId("test2");
        searchVaildAuthMemberDto.setKey("");
        searchVaildAuthMemberDto.setFlag("OutSend");
        // when
        boolean result = memberService.memberUpdateAuth(searchVaildAuthMemberDto);
        // then
        assertThat(result).isEqualTo(true);

    }
*/
    @Test
    public void testest(){
        //param();
    }

}