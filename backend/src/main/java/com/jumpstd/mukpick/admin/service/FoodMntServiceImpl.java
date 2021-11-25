package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dao.FoodMntDao;
import com.jumpstd.mukpick.admin.domain.FoodMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import com.jumpstd.mukpick.admin.exception.NoValidFoodNoException;
import com.jumpstd.mukpick.common.exception.ErrorCode;
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
        if(fudto.getIsShow() != 'Y' && fudto.getIsShow() != 'N') return null;
        int successFlag = foodMntDao.updateFoodInfo(fudto);
        System.out.println("여기 발생!" + successFlag);
        if(successFlag == 0){
            throw new NoValidFoodNoException(ErrorCode.NO_VALID_FOOD_NO);
        }

        FoodMntDomain foodInfo = foodMntDao.findByFoodNo(fudto.getFoodNo());
        return foodInfo.getFoodMntDto();
    }

    @Override
    public int deleteFoodInfo(Long foodNo) {
        return foodMntDao.deleteFoodInfo(foodNo);
    }
}
