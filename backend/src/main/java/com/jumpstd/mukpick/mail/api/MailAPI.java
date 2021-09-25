package com.jumpstd.mukpick.mail.api;

import com.jumpstd.mukpick.mail.dto.MailDto;
import com.jumpstd.mukpick.mail.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;

@Controller
public class MailAPI {

    private MailService mailService;

    @GetMapping("/mail")
    public void mailSend(MailDto mailDto){
        try {
            mailService.mailSend(mailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
