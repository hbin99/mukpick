package com.jumpstd.mukpick.admin.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class FoodUpdateRequestDto {
    private Long foodNo;
    private String foodName;
    private char isShow;

}


