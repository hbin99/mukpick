package com.jumpstd.mukpick.admin.domain;

import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
@Getter
@AllArgsConstructor
@ToString
public class FoodMntDomain {
    private Long foodNo;
    private String foodName;
    private char isShow;
    private char isDefault;
    private Date registerDate;

    public FoodResponseDto getFoodMntDto(){
        return new FoodResponseDto(foodNo,foodName,isShow,isDefault,registerDate);
    }
}
