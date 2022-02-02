package com.jumpstd.mukpick.admin.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class FoodResponseDto {
    private Long foodNo;
    private String foodName;
    private char isShow;
    private char isDefault;
    private OffsetDateTime registerDate;

}
