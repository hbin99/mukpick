package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;
import com.jumpstd.mukpick.member.dao.MemberDao;
import com.jumpstd.mukpick.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MailService mailService;

    @Override
    public int checkUserId(MemberDto memberDto){ return memberDao.checkUser(memberDto);}

    //인증코드 난수 생성
    private String getAuthCode(){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num =0 ;
        while(buffer.length() < 6){
            num = random.nextInt(10);
            buffer.append(num);
        }
        return buffer.toString();
    }

    @Override
    public Map<String,Object> register(MemberDto memberDto){
        Map<String,Object> resultMap = new HashMap<>();
        // 1 : 시스템관리자
        // 2 : 일반유저
        // 3 : 탈퇴유저
        // 4 : 정지유저
        // 5 : 회원가입 전단계 (이메일 확인 후 role_type 2로 변경)
        memberDto.setRoleType('5');//role_type
        int registerFlag = memberDao.register(memberDto);
        if(registerFlag == 1){
            resultMap = memberMailSend(memberDto, "RegisterSend");
        }else{
            resultMap.put("RESULT_MSG", "회원가입이 실패되었습니다.\n관리자에게 문의부탁드립니다.");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public int update(MemberDto memberDto){
        return memberDao.update(memberDto);
    }

    @Override
    public Map<String,Object> passwordFind(MemberDto memberDto){
        Map<String,Object> resultMap = new HashMap<>();

        //1. 회원정보가 존재하는지 유무
        int userChk = memberDao.checkUser(memberDto);
        if(userChk == 1){
            //존재한다면 임시 비밀번호 전송
            memberMailSend(memberDto,"PassSend");
            //2. 회원 임시비밀번호 update
            int updateChk = memberDao.update(memberDto);
            if(updateChk == 1){
                resultMap.put("RESULT_MSG",memberDto.getUserId()+ "님의 임시비밀번호가 발급되었습니다.");
            }else{
                resultMap.put("RESULT_MSG", "실패되었습니다.\n관리자에게 문의부탁드립니다.");
                return resultMap;
            }
        }else{
            resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 정보가 존재하지 않습니다.");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> userIdFind(MemberDto memberDto){
        Map<String,Object> resultMap = new HashMap<>();
        String userId = memberDao.userIdFind(memberDto);
        if(userId != null  ){
            resultMap.put("RESULT_MSG",memberDto.getUserId());
        }else{
            resultMap.put("RESULT_MSG", "[먹픽]에 없는 먹찌예요. 회원가입을 해주세요.");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> memberOutSend(MemberDto memberDto){
        Map<String,Object> resultMap = memberMailSend(memberDto,"OutSend");

        if(resultMap.get("CODE") == "S"){
            resultMap.put("RESULT_MSG",memberDto.getUserId()+ "님의 회원탈퇴 관련 메일을 보냈습니다. ");
        }else{
            resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 메일전송이 실패되었습니다.\n관리자에게 문의부탁드립니다.");
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> memberOut(MemberDto memberDto){
        Map<String,Object> resultMap = new HashMap<>();
        //1. 회원의 비밀번호가 동일한지 조회
        int userChk = memberDao.checkUser(memberDto);
        if(userChk == 1){
            memberDto.setRoleType('3');
            //탈퇴
            int updateUSerRole = memberDao.update(memberDto);

            if(updateUSerRole == 1){
                resultMap.put("RESULT_MSG",memberDto.getUserId()+ "님의 회원탈퇴를 성공적으로 처리했습니다. ");
            }else{
                resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 회원탈퇴 처리가 실패되었습니다.\n관리자에게 문의부탁드립니다.");
            }
        }else{
            resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 비밀번호가 맞지 않습니다.");
            return resultMap;
        }
        return resultMap;
    }

    public Map<String,Object> memberMailSend(MemberDto memberDto, String mailFlag){
        Map<String,Object> resultMap = new HashMap<>();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(memberDto.getEmail());
        //임시비밀번호 발급 메일
        if(mailFlag.equals("PassSend")){
            mailDto.setTitle("[먹픽] 임시비밀번호 발급 메일입니다.");
            StringBuffer context = new StringBuffer();
            context.append("안녕하세요. 회원님께서 요청하신 임시 비밀번호를 알려드립니다. \n");
            context.append("회원님의 임시 비밀번호는 ");
            context.append(getAuthCode());
            context.append("입니다.\n");
            mailDto.setContext(context.toString());
        }else if(mailFlag.equals("OutSend")){ //회원탈퇴 확인 메일
            mailDto.setTitle("[먹픽] 회원탈퇴를 위한 확인 메일입니다.");
            StringBuffer context = new StringBuffer();
            context.append("안녕하세요. 회원님께서 요청하신 회원탈퇴를 위한 확인메일입니다.\n");
            context.append("아래 링크에 접속하셔서 회원님의 비밀번호를 입력해주세요.\n");
            context.append("비밀번호 입력 성공시 회원탈퇴가 성공적으로 처리 됩니다.\n");
            context.append("그동안 [먹픽]을 이용해주셔서 감사합니다. 더 나은 [먹픽]이 되도록 노력하겠습니다.\n ");
            mailDto.setContext(context.toString());
        }else if(mailFlag.equals("RegisterSend")){//회원가입 후 메일 전송
            mailDto.setTitle("[먹픽] 회원가입를 위한 확인 메일입니다.");
            StringBuffer context = new StringBuffer();
            context.append("안녕하세요. 회원님 가입해주셔서 감사합니다. \n");
            context.append("아래 링크에 접속하시면 회원가입이 완료됩니다. \n");
            mailDto.setContext(context.toString());
        }
        resultMap = mailService.mailSend(mailDto);
        return resultMap;
    }

}
