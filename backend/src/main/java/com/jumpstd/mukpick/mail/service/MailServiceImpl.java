package com.jumpstd.mukpick.mail.service;

import com.jumpstd.mukpick.config.MailConfig;
import com.jumpstd.mukpick.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender javaMailSender;
    private MailConfig mailConfig;


    public Map<String,Object> mailSend(MailDto mailDto)  {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            //받는사람 메일
            messageHelper.setFrom("hyebin9612@gmail.com");
            messageHelper.setTo(mailDto.getAddress());
            // 메일 제목
            messageHelper.setSubject(mailDto.getTitle().toString());
            // 메일 내용
            messageHelper.setText(mailDto.getContext().toString(),true);

            System.out.println(mailDto.getContext());
            // send the message
            javaMailSender.send(message);

            resultMap.put("RESULT_FLAG", "SUCCESS");
            resultMap.put("RESULT_MSG", "성공적으로 메일을 전송했습니다.");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("RESULT_FLAG", "ERROR");
            resultMap.put("RESULT_MSG", "메일 전송에 실패했습니다.");
        }
        return resultMap;
    }


}
