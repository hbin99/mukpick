package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dao.FoodMntDao;
import com.jumpstd.mukpick.admin.domain.FoodMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodMntServiceImpl implements FoodMntService{
    @Autowired
    FoodMntDao foodMntDao;

    @Override
    public List<FoodResponseDto> findFoodMntList(FoodRequestDto fdto) {
        List<FoodMntDomain> findFoodMntList = foodMntDao.findList(fdto);
        List<FoodResponseDto> resultList = new ArrayList();

        for (FoodMntDomain domain:findFoodMntList) {
            resultList.add(domain.getFoodMntDto());
        }

        return resultList;
    }

    @Override
    public FoodResponseDto findFoodInfoByFoodNo(Long foodNo) {
        FoodMntDomain foodInfo = foodMntDao.findByFoodNo(foodNo);
        if(foodInfo == null){
            return null;
        }

        return foodInfo.getFoodMntDto();
    }

    @Override
    public FoodResponseDto modifyFoodInfo(FoodUpdateRequestDto fudto) {
        int successFlag = foodMntDao.updateFoodInfo(fudto);

        if(successFlag == 1){
            FoodMntDomain foodInfo = foodMntDao.findByFoodNo(fudto.getFoodNo());
            return foodInfo.getFoodMntDto();
        }

        return null;
    }

    @Override
    public int deleteFoodInfo(Long foodNo) {
        return foodMntDao.deleteFoodInfo(foodNo);
    }
}
