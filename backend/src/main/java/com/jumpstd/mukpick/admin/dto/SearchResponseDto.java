package com.jumpstd.mukpick.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class SearchResponseDto {
    private Long searchNo;
    private String searchText;
    private OffsetDateTime validDate;
    private OffsetDateTime registerDate;
    private Long searchCnt;
    private Boolean hasNext;

    public void changeHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}
