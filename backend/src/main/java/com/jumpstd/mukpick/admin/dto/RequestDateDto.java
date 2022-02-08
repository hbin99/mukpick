package com.jumpstd.mukpick.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
@RequiredArgsConstructor
@ToString
@Getter
public class RequestDateDto {
    private OffsetDateTime date;
}
