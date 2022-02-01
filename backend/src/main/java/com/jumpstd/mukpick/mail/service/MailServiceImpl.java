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


    public boolean mailSend(MailDto mailDto)  {
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

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
