package com.jumpstd.mukpick.mail.service;

import com.jumpstd.mukpick.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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


    public Map<String,Object> mailSend(MailDto mailDto) {
        Map<String,Object> resultMap = new HashMap<>();

        SimpleMailMessage message = new SimpleMailMessage();
        //받는사람 메일
        message.setTo(mailDto.getAddress());
        // 메일 제목
        message.setSubject(mailDto.getTitle());
        // 메일 내용
        message.setText(mailDto.getContext());
        System.out.println(mailDto.getContext());
        // send the message
        javaMailSender.send(message);

        resultMap.put("CODE","S");
        resultMap.put("RESULT_MSG","성공적으로 메일을 전송했습니다.");

        return resultMap;
    }


}
