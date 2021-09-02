package com.jumpstd.mukpick.admin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter @Setter
@ToString
public class SearchRequestDto {
    private Long searchNo;
    private String searchText;
    private int start;
    private int end;
    private boolean asc;
}
