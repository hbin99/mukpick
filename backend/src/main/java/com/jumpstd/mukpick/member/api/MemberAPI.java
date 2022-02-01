package com.jumpstd.mukpick.member.api;


import com.jumpstd.mukpick.member.dto.*;
import com.jumpstd.mukpick.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/member/**")
@Slf4j
public class MemberAPI {

    @Autowired
    private MemberService memberService;

    /**
     *
     * 아이디 중복체크 (회원가입)
     * @param userId
     * @return
     */
    @GetMapping("/register/{userid}")
    public ResponseEntity<Integer> checkUserId(@PathVariable("userid") String userId){
        return ResponseEntity.ok(memberService.findByUser(userId));
    }

    /**
     * 회원가입
     * 회원가입 확인 메일 전송(로그인 가능한 단계 X) > 메일 확인 클릭 > 로그인 가능한 단계로 update
     * @param
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody MemberRegisteDto memberRegisteDto){
        return ResponseEntity.ok(memberService.register(memberRegisteDto));
    }

    /**
     * 비밀번호 찾기 (메일전송)
     * 회원정보가 존재하는지 유무 > 존재하면 메일전송 > 메일 확인 클릭 > 비밀번호 입력 페이지 open
     *
     * @param
     * @return
     */
    @PostMapping("/password-find-mail")
    public ResponseEntity<Integer> passwordFind(@RequestBody SearchVaildMemberDto searchVaildMemberDto){
        return ResponseEntity.ok(memberService.passwordFind(searchVaildMemberDto));
    }

    /**
     * 아이디 찾기
     * @param
     * @return
     */
    @PostMapping("/user-find")
    public ResponseEntity<String> findByUserId(@RequestBody SearchUserIdDto searchUserIdDto){
        return ResponseEntity.ok(memberService.findByUserId(searchUserIdDto));
    }

    /**
     * 회원정보 수정
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody MemberInfoDto memberInfoDto){
        return ResponseEntity.ok(memberService.update(memberInfoDto));
    }

    /**
     * 회원탈퇴하기 전 메일 전송
     * @param
     * @return
     */
    @PostMapping("/drop-user-mail")
    public ResponseEntity<Integer> dropByUserMail(@PathVariable("userid") String userId){
        return ResponseEntity.ok(memberService.dropByUserMail(userId));
    }


    /**
     * 회원탈퇴(메일 클릭 후 탈퇴완료)
     * 비밀번호 찾기 (메일 클릭 후 비밀번호 변경창)
     * 회원가입 완료 (로그인 가능)
     * @return
     */
    @GetMapping("/{flag}/{key}/{userId}")
    public ResponseEntity<Boolean> memberUpdateAuth(
           // @PathVariable("flag") String flag,
           // @PathVariable("key") String key,
           // @PathVariable("userId") String user_id
            @RequestBody SearchVaildAuthMemberDto searchVaildAuthMemberDto
            ){
        return ResponseEntity.ok(memberService.memberUpdateAuth(searchVaildAuthMemberDto));
    }
   /*
   @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        Map<String,Object> resultMap =  memberService.login(loginDto);

        return ResponseEntity.badRequest().body("인증되지 않는 사용자입니다. ");
    }*/

}
