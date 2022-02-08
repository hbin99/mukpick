package com.jumpstd.mukpick.admin.mapper;

import com.jumpstd.mukpick.admin.domain.FoodMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMntMapper {
    List<FoodMntDomain> findList(FoodRequestDto foodRequestDto);

    FoodMntDomain findByFoodNo(Long foodNo);

    int updateFoodInfo(FoodUpdateRequestDto foodUpdateRequestDto);

    int deleteFoodInfo(Long foodNo);

}
