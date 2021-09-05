package com.jumpstd.mukpick.mail.service;

import com.jumpstd.mukpick.mail.dto.MailDto;

import java.util.Map;

public interface MailService {
    public Map<String,Object> mailSend(MailDto mailDto);
}
