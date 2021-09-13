package com.jumpstd.mukpick.member.api;

import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;

import com.jumpstd.mukpick.member.dto.MemberDto;
import com.jumpstd.mukpick.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/member/**")
public class MemberAPI {

    @Autowired
    private MemberService MemberService;
    @Autowired
    private MailService mailService;

    @GetMapping("/register")
    public String joinForm(){
        return "";
    }
    @GetMapping("/update")
    public String updateForm(){
        return "";
    }

    //아이디 중복체크
    @GetMapping("/register/{userid}")
    public Map<String,Object> checkUserId(//@PathVariable("userid") String user_id
                                          MemberDto memberDto ){
        int resultFlag = MemberService.checkUserId(memberDto);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(resultFlag == 1){
            resultMap.put("RESULT_MSG", "이미 사용중이거나 탈퇴한 아이디입니다.");
        }else{
            resultMap.put("RESULT_MSG", "멋진 아이디네요!");
        }
        resultMap.put("RESULT_FLAG", resultFlag );
        return resultMap;
    }

    //회원가입
    @PostMapping("/register")
    public Map<String,Object> register(MemberDto memberDto){
        Map<String,Object> resultMap = MemberService.register(memberDto);
        return resultMap;
    }

    //비밀번호 찾기
    @PostMapping("/password-find")
    public Map<String,Object>  passwordFind(MemberDto memberDto){
        Map<String,Object> passwordFind = MemberService.passwordFind(memberDto);
        return passwordFind;
    }

    //아이디 찾기
    @PostMapping("/user-find")
    public Map<String,Object> userIdFind(MemberDto memberDto){
        Map<String,Object> userIdFind = MemberService.userIdFind(memberDto);
        return userIdFind;
    }

    //회원정보 수정
    @PostMapping("/update")
    public Map<String,Object> update(MemberDto memberDto){
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

    //회원탈퇴 관련 메일 전송
    public Map<String,Object> memberOutMailSend(MemberDto memberDto){
        Map<String,Object> resultMap =  MemberService.memberOutSend(memberDto);
        return resultMap;
    }

    //회원 탈퇴
    @PatchMapping("/member/{userId}")
    public Map<String,Object> memberOut(MemberDto memberDto){
        Map<String,Object> resultMap =  MemberService.memberOut(memberDto);
        return resultMap;
    }
    @GetMapping("/member/{flag}/{key}/{userId}")
    public Map<String,Object> memberCheckAuth(
            @PathVariable("userId") String user_id,
            @PathVariable("key") String key,
            @PathVariable("flag") String flag){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",user_id);
        paramMap.put("key",key);
        paramMap.put("flag",flag);
        Map<String,Object> resultMap =  MemberService.memberCheckAuth(paramMap);
        return resultMap;
    }

}
