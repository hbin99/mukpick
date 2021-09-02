package com.jumpstd.mukpick.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class SearchValidDateRequestDto {
    Long searchNo;
    @DateTimeFormat(pattern="yyyyMMddHHmmss")
    Date ChangeDate;
}
