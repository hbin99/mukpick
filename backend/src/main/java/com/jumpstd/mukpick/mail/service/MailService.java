package com.jumpstd.mukpick.mail.service;

import com.jumpstd.mukpick.mail.dto.MailDto;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {
    boolean mailSend(MailDto mailDto) throws MessagingException;
}
