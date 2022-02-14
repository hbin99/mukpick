package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;

import java.util.List;

public interface FoodMntService {
    List<FoodResponseDto> findFoodMntList(FoodRequestDto fdto);

    FoodResponseDto findFoodInfoByFoodNo(Long foodNo);

    FoodResponseDto modifyFoodInfo(FoodUpdateRequestDto fudto);

    int deleteFoodInfo(Long foodNo);

    boolean chkDupleByFoodName(String foodName);
}
