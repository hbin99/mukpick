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

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MailService mailService;

    @Override
    public int findByUser(String userId){
        SearchVaildMemberDto svmDto = new SearchVaildMemberDto();
        svmDto.setUserId(userId);
        //return memberDao.findByUser(svmDto).orElseThrow(UserNotFoundException ::new);
        return memberDao.findByUser(svmDto);
    }

    @Override
    public int update(MemberInfoDto memberInfoDto){
        return memberDao.update(memberInfoDto);
    }

    @Override
    public MemberInfoDto findByUserData(String userId){
        return memberDao.findByUserData(userId);
    }

    @Override
    public int register(MemberRegisteDto memberRegisteDto){

        PasswordHash passwordHash= new PasswordHash();
        try {
            memberRegisteDto.setRoleType(RollType.BEFORE_SING_UP_USER.getValue());//회원가입 전단계
            memberRegisteDto.setPassword(passwordHash.getPassword(memberRegisteDto.getPassword().toString()));

            int register = memberDao.register(memberRegisteDto);
            if(register == 1){
                SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
                sendMailMemberDto.setFlag("RegisterSend");
                sendMailMemberDto.setUserId(memberRegisteDto.getUserId());
                sendMailMemberDto.setEmail(memberRegisteDto.getEmail());
                if(memberMailSend(sendMailMemberDto)){
                    return 1;
                }
            }else{
                return 0;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int passwordFind(SearchVaildMemberDto searchVaildMemberDto){
        int userChk = memberDao.findByUser(searchVaildMemberDto);
        if (userChk == 1) {
            SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
            sendMailMemberDto.setEmail(searchVaildMemberDto.getEmail());
            sendMailMemberDto.setFlag("PassSend");
            sendMailMemberDto.setUserId(searchVaildMemberDto.getUserId());
            System.out.println(memberMailSend(sendMailMemberDto));
            if(!memberMailSend(sendMailMemberDto)){
                return 0;
            }
        } else {
            return 0;
        }

        return 1;
    }

    @Override
    public String findByUserId(SearchUserIdDto searchUserIdDto){
        return memberDao.findByUserId(searchUserIdDto);
    }

    @Override
    public int dropByUserMail(String userId){
        //회원에 대한 정보를 조회하고
        MemberInfoDto memberInfoDto = memberDao.findByUserData(userId);
        //정보를 가지고 메일 전송
        SendMailMemberDto sendMailMemberDto = new SendMailMemberDto();
        sendMailMemberDto.setEmail(memberInfoDto.getEmail());
        sendMailMemberDto.setFlag("OutSend");
        sendMailMemberDto.setUserId(memberInfoDto.getUserId());
        if(!memberMailSend(sendMailMemberDto)){
            return 0;
        }
        return 1;
    }

    public boolean memberMailSend(SendMailMemberDto sendMailMemberDto){

        MailDto mailDto = new MailDto();
        mailDto.setAddress(sendMailMemberDto.getEmail());

        MemberInfoDto memberInfoDto = new MemberInfoDto();
        StringBuffer context = new StringBuffer();
        PasswordHash passwordHash= new PasswordHash();
        try {
            // 코드 발급 메일
            String authKey = passwordHash.passSplice(sendMailMemberDto.getUserId());
            memberInfoDto.setUserId(sendMailMemberDto.getUserId());
            memberInfoDto.setAuthKey(authKey);
            memberDao.update(memberInfoDto);
            if(sendMailMemberDto.getFlag().equals("PassSend")){

                mailDto.setTitle("[먹픽] 비밀번호 변경을 위한 확인 메일입니다.");
                context.append("안녕하세요. 회원님께서 요청하신 비밀번호 변경을 위한 확인메일입니다.</br>");
                context.append("아래 링크에 접속하셔서 회원님의 비밀번호를 변경해주세요</br>");
                //비밀번호 입력 페이지로 이등하게끔 수정필요
                context.append("<h1>비밀번호 변경 </h1> \n");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberInfoDto.getUserId());
                context.append("' target='_blenk'>비밀번호 변경 이동</a></br>");

                mailDto.setContext(context.toString());
            }else if(sendMailMemberDto.getFlag().equals("OutSend")){ //회원탈퇴 확인 메일
                mailDto.setTitle("[먹픽] 회원탈퇴를 위한 확인 메일입니다.");
                context.append("안녕하세요. 회원님께서 요청하신 회원탈퇴를 위한 확인메일입니다.</br>");
                context.append("아래 링크에 접속하시면 회원탈퇴가 성공적으로 처리 됩니다.</br>");
                context.append("그동안 [먹픽]을 이용해주셔서 감사합니다. 더 나은 [먹픽]이 되도록 노력하겠습니다.</br> ");
                context.append("<h1>메일인증</h1>");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberInfoDto.getUserId());
                context.append("' target='_blenk'>이메일 인증 확인</a></br>");
                mailDto.setContext(context.toString());

            }else if(sendMailMemberDto.getFlag().equals("RegisterSend")){//회원가입 후 메일 전송
                mailDto.setTitle("[먹픽] 회원가입를 위한 확인 메일입니다.");
                context.append("<h2><span style = 'color:darkcyan'>메일인증</span> 안내입니다.</h2><br/>");
                context.append("안녕하세요. [먹픽]을 이용해주셔서 진심으로 감사합니다.<br/>");
                context.append("회원님,아래 메일 인증 링크에 클릭하여 회원가입을 완료해주세요.<br/>");
                context.append("<a href='http://localhost:8081/api/member/"+ sendMailMemberDto.getFlag() +"/"+authKey +"/"+memberInfoDto.getUserId());
                context.append("' target='_blenk'>이메일 인증 확인</a><br/>");
                mailDto.setContext(context.toString());
            }

             if(!mailService.mailSend(mailDto)){
                 return false;
             }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public boolean memberUpdateAuth(SearchVaildAuthMemberDto searchVaildAuthMemberDto){
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        //String text ="";
        int userChk = memberDao.userAuthCheck(searchVaildAuthMemberDto);
        if (userChk == 1) {
            if ("RegisterSend".equals(searchVaildAuthMemberDto.getFlag())) {//회원가입
                memberInfoDto.setRoleType(RollType.USER.getValue());
                //text ="회원가입";
            }else if("OutSend".equals(searchVaildAuthMemberDto.getFlag())) {//탈퇴
                memberInfoDto.setRoleType(RollType.DROP_USER.getValue());
                //text ="회원 탈퇴";
            }else if("PassSend".equals(searchVaildAuthMemberDto.getFlag())){
                //text ="비밀번호 찾기";
            }else{
                //searchVaildAuthMemberDto.getFlag()가 없음
                return false;
            }
            memberInfoDto.setUserId(searchVaildAuthMemberDto.getUserId());
            memberInfoDto.setAuthKey("");
            int update = memberDao.update(memberInfoDto);
            if(update != 1){
                return false;
            }
        }else{//key가 없을때
            return false;
        }

        return true;
    }
/*
    public Map<String,Object> login(LoginDto loginDto)  {
        Map<String,Object> resultMap = new HashMap<>();

        SearchVaildMemberDto searchVaildMemberDto = new SearchVaildMemberDto();
        searchVaildMemberDto.setUserId(loginDto.getUserId());
        PasswordHash passwordHash  = new PasswordHash();
        try {
            MemberDto memberDto = memberDao.findByUserData(loginDto.getUserId());
            //회원의 정보 조회
            if (memberDto == null) {
                resultMap.put("RESULT_FLAG","ERROR");
                resultMap.put("RESULT_MSG", "로그인에 실패했습니다.");
                return resultMap;
            } else {
                //비밀번호가 맞는지 조회
                boolean matched = passwordHash.getVaildPassword(loginDto.getPassword(), memberDto.getPassword());
                System.out.println("matched: "+matched);
                if(matched){//비밀번호 동일하다면
                    resultMap.put("RESULT_FLAG","SUCCESS");
                    resultMap.put("RESULT_MSG", "로그인에 성공했습니다.");
                }else{
                    resultMap.put("RESULT_FLAG","ERROR");
                    resultMap.put("RESULT_MSG", "로그인에 실패했습니다.");
                    return resultMap;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
*/
}
