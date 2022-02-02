package com.jumpstd.mukpick.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class SearchValidDateRequestDto {
    Long searchNo;

    OffsetDateTime ChangedDate;
}
