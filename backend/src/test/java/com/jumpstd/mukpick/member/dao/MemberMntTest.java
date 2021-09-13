package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;
import com.jumpstd.mukpick.member.dto.MemberDto;
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
@Transactional
@DisplayName("Test")
class MemberMntTest {
    @Autowired
    MemberMapper mapper;

    @Autowired
    MemberService memberService;

    @Autowired
    MailService mailService;

    @BeforeEach
    @DisplayName("Member insert")
    public void param(){
        MemberDto dto = new MemberDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("hyebin9613@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-1111");
        dto.setGender('F');
        dto.setRoleType('2');
        dto.setUserName("테스트");

        mapper.register(dto);
    }

    @Test
    @DisplayName("아이디 중복체크")
    public void chkUserId(){
        String user_id ="test";
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(user_id);
        int chk = memberService.checkUserId(memberDto);

        System.out.println(chk);
        assertThat(chk).isNotZero();
    }
    @Test
    @DisplayName("회원가입 신청 후 메일 전송")
    public void registerSendMail(){
        MemberDto dto = new MemberDto();
        dto.setUserId("test3");
        dto.setAge(23);
        dto.setEmail("hyebin9612@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-2222");
        dto.setGender('F');
        dto.setRoleType('2');
        dto.setUserName("테스트");

        Map<String,Object> returnMap = memberService.register(dto);

        assertThat(returnMap.get("CODE")).isEqualTo("S");
        System.out.println(returnMap.get("RESULT_MSG"));

    }

    @Test
    @DisplayName("회원가입 메일 클릭시 ")
    public void register() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test2");
        memberDto.setRoleType('2');
        // when
        memberService.update(memberDto);
        // then
        List<MemberDto> list = mapper.userList(memberDto);
        for(MemberDto dto : list){
            System.out.println("회원정보 : " + dto.toString());
        }
    }
    @Test
    @DisplayName("아이디 찾기")
    public void userIdFind() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserName("테스트");
        memberDto.setEmail("hyebin9612@gmail.com");
        memberDto.setPhone("010-2222-1111");
        // when
        Map<String,Object> returnMap = memberService.userIdFind(memberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("임시비밀번호 발급")
    public void passwordFind() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test2");
        memberDto.setEmail("hyebin9612@gmail.com");
        // when
        Map<String,Object> returnMap =memberService.passwordFind(memberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("회원정보 수정")
    public void update() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test2");
        memberDto.setEmail("hyebin9612@gmail.com");
        memberDto.setPhone("010-1234-5678");
        memberDto.setAge(13);
        // when
        memberService.update(memberDto);
        // then
        List<MemberDto> list = mapper.userList(memberDto);
        for(MemberDto dto : list){
            System.out.println("회원정보 : " + dto.toString());
        }
    }
    @Test
    @DisplayName("회원탈퇴 메일 전송")
    public void memberOutSend() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test2");
        memberDto.setPassword("test123");
        memberDto.setEmail("hyebin9612@gmail.com");
        // when
        Map<String,Object> returnMap = memberService.memberOutSend(memberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("회원탈퇴")
    public void memberOut() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test2");

        // when
        Map<String,Object> returnMap = memberService.memberOut(memberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }

    @Test
    public void testest(){

       /* MemberDto dto = new MemberDto();
        dto.setUser_id("test1");
        dto.setAge(23);
        dto.setEmail("test1@gmail.com");
        dto.setPassword("test123");
        dto.setProfile_img("");
        dto.setPhone("010-1111-1111");
        dto.setGender('F');
        dto.setRole_type('2');
        dto.setUser_name("테스트");*/
        MailDto mailDto = new MailDto();
        mailDto.setTitle("테스트 메일입니다.");
        mailDto.setContext("테스트 메일입니다.");
        mailDto.setAddress("hyebin9612@gmail.com");
        mailService.mailSend(mailDto);
        // System.out.println(mapper.register(dto));

    }

}