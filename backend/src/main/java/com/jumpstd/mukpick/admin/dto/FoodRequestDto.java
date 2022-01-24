package com.jumpstd.mukpick.admin.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class FoodRequestDto {
    private String foodName;
    private char isShow;
    private char isDefault;
    private int start;
    private int end;
    private boolean asc;
}
