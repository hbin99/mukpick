package com.jumpstd.mukpick.member.api;

import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.mail.service.MailService;

import com.jumpstd.mukpick.member.dto.*;
import com.jumpstd.mukpick.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/member/**")
@Slf4j
public class MemberAPI {

    @Autowired
    private MemberService memberService;

    /**
     *
     * 아이디 중복체크 (회원가입)
     * @param user_id
     * @return
     */
    @GetMapping("/register/{userid}")
    public ResponseEntity checkUserId(@PathVariable("userid") String user_id){

        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId(user_id);
        Map<String, Object> resultMap  = memberService.checkUserId(searchVaildMemberDto);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 회원가입
     * 회원가입 확인 메일 전송(로그인 가능한 단계 X) > 메일 확인 클릭 > 로그인 가능한 단계로 update
     * @param memberDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MemberDto memberDto){
        Map<String,Object> resultMap = memberService.register(memberDto);
        return ResponseEntity.ok(resultMap);
    }

    /**
     * 비밀번호 찾기 (메일전송)
     * 회원정보가 존재하는지 유무 > 존재하면 메일전송 > 메일 확인 클릭 > 비밀번호 입력 페이지 open
     *
     * @param searchVaildMemberDto
     * @return
     */
    @PostMapping("/password-find-mail")
    public ResponseEntity passwordFind(@RequestBody SearchVaildMemberDto searchVaildMemberDto){
        System.out.println(searchVaildMemberDto.getUserId());
        Map<String,Object> passwordFind = memberService.passwordFind(searchVaildMemberDto);
        return ResponseEntity.ok(passwordFind);
    }

    /**
     * 아이디 찾기
     * @param
     * @return
     */
    @PostMapping("/user-find")
    public ResponseEntity userIdFind(@RequestBody SearchUserIdMemberDto searchUserIdMemberDto){

        Map<String,Object> userIdFind = memberService.userIdFind(searchUserIdMemberDto);
        return ResponseEntity.ok(userIdFind);
    }

    /**
     * 회원정보 수정
     * @param memberDto
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody MemberDto memberDto){
        Map<String,Object> resultMap = memberService.update(memberDto);
        return ResponseEntity.ok(resultMap);
    }

    /**
     * 회원탈퇴하기 전 메일 전송
     * @param
     * @return
     */
    @PostMapping("/drop-user-mail")
    public ResponseEntity dropByUserMail(@PathVariable("userid") String user_id){
        Map<String,Object> resultMap =  memberService.dropByUserMail(user_id);
        return ResponseEntity.ok(resultMap);
    }


    /**
     * 회원탈퇴(메일 클릭 후 탈퇴완료)
     * 비밀번호 찾기 (메일 클릭 후 비밀번호 변경창)
     * 회원가입 완료 (로그인 가능)
     * @return
     */
    @GetMapping("/{flag}/{key}/{userId}")
    public ResponseEntity memberUpdateAuth(
            @PathVariable("flag") String flag,
            @PathVariable("key") String key,
            @PathVariable("userId") String user_id
            ){
        SearchVaildAuthMemberDto searchVaildAuthMemberDto = new SearchVaildAuthMemberDto();
        searchVaildAuthMemberDto.setFlag(flag);
        searchVaildAuthMemberDto.setKey(key);
        searchVaildAuthMemberDto.setUserId(user_id);

        Map<String,Object> resultMap =  memberService.memberUpdateAuth(searchVaildAuthMemberDto);
        return ResponseEntity.ok(resultMap);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        Map<String,Object> resultMap =  memberService.login(loginDto);

        return ResponseEntity.badRequest().body("인증되지 않는 사용자입니다. ");
    }

}
