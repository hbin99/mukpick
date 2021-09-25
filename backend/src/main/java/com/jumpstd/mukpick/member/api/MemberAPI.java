package com.jumpstd.mukpick.member.api;

import com.jumpstd.mukpick.mail.service.MailService;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.dto.SearchUserIdMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildAuthMemberDto;
import com.jumpstd.mukpick.member.dto.SearchVaildMemberDto;
import com.jumpstd.mukpick.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/member/**")
@Slf4j
public class MemberAPI {

    @Autowired
    private MemberService MemberService;

    @GetMapping("/register")
    public String joinForm(){
        return "";
    }
    @GetMapping("/update")
    public String updateForm(){
        return "";
    }

    /**
     *
     * 아이디 중복체크 (회원가입)
     * @param user_id
     * @return
     */
    @GetMapping("/register/{userid}")
    public Map<String,Object> checkUserId(@PathVariable("userid") String user_id){
        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId(user_id);
        int resultFlag = MemberService.checkUserId(searchVaildMemberDto);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(resultFlag == 1){
            resultMap.put("RESULT_MSG", "이미 사용중이거나 탈퇴한 아이디입니다.");
        }else{
            resultMap.put("RESULT_MSG", "멋진 아이디네요!");
        }
        resultMap.put("RESULT_FLAG", resultFlag );
        return resultMap;
    }

    /**
     * 회원가입
     * 회원가입 확인 메일 전송(로그인 가능한 단계 X) > 메일 확인 클릭 > 로그인 가능한 단계로 update
     * @param memberDto
     * @return
     */
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody MemberDto memberDto){
        Map<String,Object> resultMap = MemberService.register(memberDto);
        return resultMap;
    }

    /**
     * 비밀번호 찾기 (메일전송)
     * 회원정보가 존재하는지 유무 > 존재하면 메일전송 > 메일 확인 클릭 > 비밀번호 입력 페이지 open
     *
     * @param searchVaildMemberDto
     * @return
     */
    @PostMapping("/password-find-mail")
    public Map<String,Object>  passwordFind(@RequestBody SearchVaildMemberDto searchVaildMemberDto){
        Map<String,Object> passwordFind = MemberService.passwordFind(searchVaildMemberDto);
        return passwordFind;
    }

    /**
     * 아이디 찾기
     * @param
     * @return
     */
    @PostMapping("/user-find")
    public Map<String,Object> userIdFind(@RequestBody SearchUserIdMemberDto searchUserIdMemberDto){
        Map<String,Object> userIdFind = MemberService.userIdFind(searchUserIdMemberDto);
        return userIdFind;
    }

    /**
     * 회원정보 수정
     * @param memberDto
     * @return
     */
    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody MemberDto memberDto){
        int resultFlag = MemberService.update(memberDto);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //파일 등록
        if(resultFlag == 1){
            resultMap.put("RESULT_MSG",memberDto.getUserId()+ "님의 정보수정이 완료되었습니다.");
        }else{
            resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 정보수정이 실패되었습니다.\n관리자에게 문의부탁드립니다.");
        }
        resultMap.put("RESULT_FLAG", resultFlag );
        return resultMap;
    }

    /**
     * 회원탈퇴하기 전 메일 전송
     * @param
     * @return
     */
    @PostMapping("/drop-user-mail")
    public Map<String,Object> dropByUserMail(@PathVariable("userid") String user_id){
        Map<String,Object> resultMap =  MemberService.dropByUserMail(user_id);
        return resultMap;
    }


    /**
     * 회원탈퇴(메일 클릭 후 탈퇴완료)
     * 비밀번호 찾기 (메일 클릭 후 비밀번호 변경창)
     * 회원가입 완료 (로그인 가능)
     * @return
     */
    @GetMapping("/member/{flag}/{key}/{userId}")
    public Map<String,Object> memberUpdateAuth(
            @RequestBody SearchVaildAuthMemberDto
                    searchVaildAuthMemberDto){
        Map<String,Object> resultMap =  MemberService.memberUpdateAuth(searchVaildAuthMemberDto);
        return resultMap;
    }


}
