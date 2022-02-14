package com.jumpstd.mukpick.admin.dao;

import com.jumpstd.mukpick.admin.domain.FoodMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import com.jumpstd.mukpick.admin.mapper.FoodMntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodMntDaoImpl implements FoodMntDao{
    @Autowired
    FoodMntMapper foodMntMapper;

    @Override
    public List<FoodMntDomain> findList(FoodRequestDto fdto) {
        return foodMntMapper.findList(fdto);
    }

    @Override
    public FoodMntDomain findByFoodNo(Long foodNo) {
        return foodMntMapper.findByFoodNo(foodNo);
    }

    @Override
    public int updateFoodInfo(FoodUpdateRequestDto fudto) {
        return foodMntMapper.updateFoodInfo(fudto);
    }

    @Override
    public int deleteFoodInfo(Long foodNo) {
        return foodMntMapper.deleteFoodInfo(foodNo);
    }

    @Override
    public int chkDupleByFoodName(String foodName) {
        return foodMntMapper.chkDupleByFoodName(foodName);
    }
}
