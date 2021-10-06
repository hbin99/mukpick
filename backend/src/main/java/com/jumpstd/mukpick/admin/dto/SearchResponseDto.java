package com.jumpstd.mukpick.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class SearchResponseDto {
    private Long searchNo;
    private String searchText;
    private Date validDate;
    private Date registerDate;
    private Long searchCnt;


}
