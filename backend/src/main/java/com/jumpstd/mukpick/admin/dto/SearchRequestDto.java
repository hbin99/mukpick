package com.jumpstd.mukpick.admin.dto;

import lombok.*;

@RequiredArgsConstructor
@Data
public class SearchRequestDto {
    private Long searchNo;
    private String searchText;
    private int start;
    private int limit;
    private boolean asc;
}
