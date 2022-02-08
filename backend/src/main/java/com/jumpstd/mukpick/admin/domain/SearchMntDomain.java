package com.jumpstd.mukpick.admin.domain;

import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class SearchMntDomain {
    private Long searchNo;
    private String searchText;
    private OffsetDateTime validDate;
    private OffsetDateTime registerDate;
    private Long searchCnt;


    public SearchResponseDto getSearchMntDto(){
        return new SearchResponseDto(
                this.searchNo
                , this.searchText
                , this.validDate
                , this.registerDate
                , searchCnt
                , null);
        }
}