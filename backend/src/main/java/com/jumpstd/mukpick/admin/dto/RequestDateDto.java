package com.jumpstd.mukpick.admin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@RequiredArgsConstructor
@ToString
@Getter
public class RequestDateDto {
    @DateTimeFormat(pattern="yyyyMMddHHmmss")
    private Date date;
}
