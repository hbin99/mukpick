package com.jumpstd.mukpick.member.service;

import com.jumpstd.mukpick.config.RollType;
import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;
import com.jumpstd.mukpick.member.dao.MemberDao;
import com.jumpstd.mukpick.member.dto.*;
import com.jumpstd.mukpick.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MailService mailService;

    @Override
    public int checkUserId(SearchVaildMemberDto searchVaildMemberDto){ return memberDao.checkUser(searchVaildMemberDto);}

    @Override
    public Map<String,Object> register(MemberDto memberDto){

        Map<String,Object> resultMap = new HashMap<>();
        PasswordHash passwordHash= new PasswordHash();
        try {
            memberDto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());//회원가입 전단계
            String passwordhashStr = passwordHash.getPassword(memberDto.getPassword().toString());
            memberDto.setPassword(passwordhashStr);

            int registerFlag = memberDao.register(memberDto);
            if(registerFlag == 1){
                SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
                sendMailMemberDto.setFlag("RegisterSend");
                sendMailMemberDto.setUserId(memberDto.getUserId());
                sendMailMemberDto.setEmail(memberDto.getEmail());

                resultMap = memberMailSend(sendMailMemberDto);
            }else{
                resultMap.put("RESULT_MSG", "회원가입이 실패되었습니다.\n관리자에게 문의부탁드립니다.");
                return resultMap;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public int update(MemberDto memberDto){
        return memberDao.update(memberDto);
    }

    @Override
    public Map<String,Object> passwordFind(SearchVaildMemberDto searchVaildMemberDto){
        Map<String,Object> resultMap = new HashMap<>();

        int userChk = memberDao.checkUser(searchVaildMemberDto);
        if(userChk == 1){
            SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
            sendMailMemberDto.setEmail(searchVaildMemberDto.getEmail());
            sendMailMemberDto.setFlag("PassSend");
            sendMailMemberDto.setUserId(searchVaildMemberDto.getUserId());

            Map<String,Object> returnMap= memberMailSend(sendMailMemberDto);

            if(returnMap.get("CODE").equals("S")){
                resultMap.put("RESULT_MSG",searchVaildMemberDto.getUserId()+ "님께 메일 전송이 완료되었습니다. /n "+ searchVaildMemberDto.getEmail());
            }else{
                resultMap.put("RESULT_MSG", "실패되었습니다.\n관리자에게 문의부탁드립니다.");
                return resultMap;
            }
        }else{
            resultMap.put("RESULT_MSG", searchVaildMemberDto.getUserId()+ "님의 정보가 존재하지 않습니다.");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> userIdFind(SearchUserIdMemberDto searchUserIdMemberDto){
        Map<String,Object> resultMap = new HashMap<>();
        String userId = memberDao.userIdFind(searchUserIdMemberDto);
        if( userId != null  ){
            resultMap.put("RESULT_MSG","회원님의 아이디는 " + userId+ "입니다.");
            resultMap.put("RESULT_FLAG",1);
        }else{
            resultMap.put("RESULT_MSG", "[먹픽]에 없는 먹찌예요. 회원가입을 해주세요.");
            resultMap.put("RESULT_FLAG",0);
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> dropByUserMail(String userId){
        //회원에 대한 정보를 조회하고
        MemberDto memberDto = memberDao.findByUserData(userId);
        //정보를 가지고 메일 전송
        SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
        sendMailMemberDto.setEmail(memberDto.getEmail());
        sendMailMemberDto.setFlag("OutSend");
        sendMailMemberDto.setUserId(memberDto.getUserId());
        Map<String,Object> resultMap = memberMailSend(sendMailMemberDto);

        if(resultMap.get("CODE") == "S"){
            resultMap.put("RESULT_MSG",memberDto.getUserId()+ "님의 회원탈퇴 관련 메일을 보냈습니다. ");
        }else{
            resultMap.put("RESULT_MSG", memberDto.getUserId()+ "님의 메일전송이 실패되었습니다.\n관리자에게 문의부탁드립니다.");
        }
        return resultMap;
    }

    public Map<String,Object> memberMailSend(SendMailMemberDto sendMailMemberDto){
        Map<String,Object> resultMap = new HashMap<>();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(sendMailMemberDto.getEmail());
        MemberDto memberDto = new MemberDto();
        StringBuffer context = new StringBuffer();
        PasswordHash passwordHash= new PasswordHash();
        try {
            // 코드 발급 메일
            String authKey = passwordHash.passSplice(sendMailMemberDto.getUserId());
            memberDto.setUserId(sendMailMemberDto.getUserId());
            memberDto.setAuthKey(authKey);
            memberDao.update(memberDto);
            if(sendMailMemberDto.getFlag().equals("PassSend")){

                mailDto.setTitle("[먹픽] 비밀번호 변경을 위한 확인 메일입니다.");
                context.append("안녕하세요. 회원님께서 요청하신 비밀번호 변경을 위한 확인메일입니다.</br>");
                context.append("아래 링크에 접속하셔서 회원님의 비밀번호를 변경해주세요</br>");
                //비밀번호 입력 페이지로 이등하게끔 수정필요
                context.append("<h1>비밀번호 변경 </h1> \n");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberDto.getUserId());
                context.append("' target='_blenk'>비밀번호 변경 이동</a></br>");

                mailDto.setContext(context.toString());
            }else if(sendMailMemberDto.getFlag().equals("OutSend")){ //회원탈퇴 확인 메일
                mailDto.setTitle("[먹픽] 회원탈퇴를 위한 확인 메일입니다.");
                context.append("안녕하세요. 회원님께서 요청하신 회원탈퇴를 위한 확인메일입니다.</br>");
                context.append("아래 링크에 접속하시면 회원탈퇴가 성공적으로 처리 됩니다.</br>");
                context.append("그동안 [먹픽]을 이용해주셔서 감사합니다. 더 나은 [먹픽]이 되도록 노력하겠습니다.</br> ");
                context.append("<h1>메일인증</h1>");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberDto.getUserId());
                context.append("' target='_blenk'>이메일 인증 확인</a></br>");
                mailDto.setContext(context.toString());

            }else if(sendMailMemberDto.getFlag().equals("RegisterSend")){//회원가입 후 메일 전송
                mailDto.setTitle("[먹픽] 회원가입를 위한 확인 메일입니다.");
                context.append("<h2><span style = 'color:darkcyan'>메일인증</span> 안내입니다.</h2><br/>");
                context.append("안녕하세요. [먹픽]을 이용해주셔서 진심으로 감사합니다.<br/>");
                context.append("회원님,아래 메일 인증 링크에 클릭하여 회원가입을 완료해주세요.<br/>");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberDto.getUserId());
                context.append("' target='_blenk'>이메일 인증 확인</a><br/>");
                mailDto.setContext(context.toString());
            }

            resultMap = mailService.mailSend(mailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    @Override
    public Map<String,Object> memberUpdateAuth(SearchVaildAuthMemberDto searchVaildAuthMemberDto){
        Map<String,Object> resultMap = new HashMap<>();
        MemberDto memberDto = new MemberDto();
        int userChk = memberDao.userAuthCheck(searchVaildAuthMemberDto);

        if("RegisterSend".equals(searchVaildAuthMemberDto.getFlag())){//회원가입
            if(userChk == 1){//회원존재
                memberDto.setUserId(searchVaildAuthMemberDto.getUserId());
                memberDto.setRoleType(RollType.USER.getValue());
                memberDto.setAuthKey("");
                memberDao.update(memberDto);
            }else{
                //실패
            }
        }else if("OutSend".equals(searchVaildAuthMemberDto.getFlag())  ){//탈퇴
            if(userChk == 1){//회원존재
                memberDto.setUserId(searchVaildAuthMemberDto.getUserId());
                memberDto.setRoleType(RollType.DROP_USER.getValue());
                memberDto.setAuthKey("");
                memberDao.update(memberDto);
            }else{
                //실패
            }
        }else{//비밀번호 찾기
            memberDto.setAuthKey("");
            memberDto.setUserId(searchVaildAuthMemberDto.getUserId());
            memberDao.update(memberDto);

        }
        return resultMap;
    }

}
