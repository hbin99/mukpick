package com.jumpstd.mukpick.admin.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@RequiredArgsConstructor
@ToString
public class FoodUpdateRequestDto {
    private Long foodNo;
    private String foodName;
    private char isShow;

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setIsShow(char isShow) {
        this.isShow = isShow;
    }

    public void setUpFoodNo(Long foodNo){
        this.foodNo = foodNo;
    }
}


