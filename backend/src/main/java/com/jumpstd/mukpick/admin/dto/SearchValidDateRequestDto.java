package com.jumpstd.mukpick.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Getter
@AllArgsConstructor
@ToString
public class SearchValidDateRequestDto {
    Long searchNo;
    Date ChangeDate;
}
