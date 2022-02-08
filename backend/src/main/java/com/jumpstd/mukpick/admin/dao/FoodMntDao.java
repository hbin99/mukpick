package com.jumpstd.mukpick.admin.dao;

import com.jumpstd.mukpick.admin.domain.FoodMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;

import java.util.List;

public interface FoodMntDao {
    List<FoodMntDomain> findList(FoodRequestDto fdto);

    FoodMntDomain findByFoodNo(Long foodNo);

    int updateFoodInfo(FoodUpdateRequestDto fudto);

    int deleteFoodInfo(Long foodNo);
}
