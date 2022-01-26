package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.config.RollType;
import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;
import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.dto.SearchUserIdMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildAuthMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildMemberDto;
import com.jumpstd.mukpick.member.mapper.MemberMapper;
import com.jumpstd.mukpick.member.service.MemberService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    /*@BeforeEach
    @DisplayName("Member insert")
    public void param(){
        MemberDto dto = new MemberDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("hyebin9612@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-1111");
        dto.setGender('F');
        dto.setRoleType(RollType.USER.getValue());
        dto.setUserName("테스트");

        mapper.register(dto);
    }*/

    @Test
    @DisplayName("id check")
    public void chkUserId(){
        String user_id ="test";
        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId(user_id);
//        int chk = memberService.checkUserId(searchVaildMemberDto);

//        System.out.println(chk);
        //assertThat(chk).isNotZero();
    }
    @Test
    @DisplayName("회원가입 신청 후 메일 전송")
    public void registerSendMail(){
        MemberDto dto = new MemberDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("dbseoyyyy@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-6666");
        dto.setGender('F');
        dto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());
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
        memberDto.setRoleType(RollType.USER.getValue());
        memberDto.setAuthKey("");
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
        SearchUserIdMemberDto searchUserIdMemberDto = new SearchUserIdMemberDto();
        searchUserIdMemberDto.setUserName("테스트");
        searchUserIdMemberDto.setEmail("tjdud1994@gmail.com");
        searchUserIdMemberDto.setPhone("010-2222-2222");
        // when
        Map<String,Object> returnMap = memberService.userIdFind(searchUserIdMemberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("비밀번호 찾기")
    public void passwordFind() {
        // given
        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId("test2");
        searchVaildMemberDto.setEmail("tjdud1994@gmail.com");
        // when
        Map<String,Object> returnMap =memberService.passwordFind(searchVaildMemberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("회원정보 수정")
    public void update() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId("test");
        memberDto.setEmail("test123@gmail.com");
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
        String user_id = "test2";
        // when
        Map<String,Object> returnMap = memberService.dropByUserMail(user_id);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));
    }
    @Test
    @DisplayName("회원탈퇴")
    public void memberOut() {
        // given
        SearchVaildAuthMemberDto searchVaildAuthMemberDto = new SearchVaildAuthMemberDto();
        searchVaildAuthMemberDto.setUserId("test2");
        searchVaildAuthMemberDto.setKey("");
        searchVaildAuthMemberDto.setFlag("OutSend");
        // when
        Map<String,Object> returnMap = memberService.memberUpdateAuth(searchVaildAuthMemberDto);
        // then
        System.out.println(returnMap.get("RESULT_MSG"));

    }

    @Test
    public void testest(){

        MemberDto dto = new MemberDto();
        dto.setUserId("test2");
        dto.setAge(23);
        dto.setEmail("dbseoyyyy@gmail.com");
        dto.setPassword("test123");
        dto.setProfileImg("");
        dto.setPhone("010-2222-6666");
        dto.setGender('F');
        dto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());
        dto.setUserName("테스트");

        /*MailDto mailDto = new MailDto();
        try {
            mailDto.setTitle("테스트 메일입니다.");
            mailDto.setContext("테스트 메일입니다.");
            mailDto.setAddress("tjdud1994@gmail.com");

            mailService.mailSend(mailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/
        memberService.register(dto);
         System.out.println("");

    }

}