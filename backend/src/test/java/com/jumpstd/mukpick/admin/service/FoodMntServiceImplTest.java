package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("음식관리 Service 테스트")
@Transactional
class FoodMntServiceImplTest {
    @Autowired
    FoodMntService foodMntService;

    @Autowired
    SearchMntService searchMntService;


    @BeforeEach
    @DisplayName("초기화 메서드 - 데이터 2개 세팅")
    public void init(){
        String text1 = "라볶이";
        searchMntService.saveSearchText(text1);

        String text2 = "마라탕";
        searchMntService.saveSearchText(text2);

        SearchResponseDto searchData1 = searchMntService.findBySearchText(text1);
        SearchResponseDto searchData2 = searchMntService.findBySearchText(text2);

        searchMntService.transferToFood(searchData1.getSearchNo());
        searchMntService.transferToFood(searchData2.getSearchNo());
    }

    @Test
    @DisplayName("음식 관리 전체 리스트 조회")
    public void findFoodList(){
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        List<FoodResponseDto> foodMntList = foodMntService.findFoodMntList(foodRequestDto);
        System.out.println("foodMntList = " + foodMntList.size());


        assertThat(foodMntList.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("음식 관리 선택 조회")
    public void findFoodByFoodName(){
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setFoodName("마라탕");
        List<FoodResponseDto> foodMntList = foodMntService.findFoodMntList(foodRequestDto);
        System.out.println("foodMntList = " + foodMntList.toString());


        assertThat(foodMntList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("음식 관리 선택 조회 - 노출여부 N")
    public void findFoodIsShowTrue(){
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setIsShow('N');
        List<FoodResponseDto> foodMntList = foodMntService.findFoodMntList(foodRequestDto);
        System.out.println("foodMntList = " + foodMntList.toString());


        assertThat(foodMntList.size()).isEqualTo(0);
    }


    @Test
    @DisplayName("음식이름 정보 변경")
    public void modifyFoodName() throws Exception {
        // given
        String searchText = "마라탕";
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setFoodName(searchText);

        List<FoodResponseDto> resultList = foodMntService.findFoodMntList(foodRequestDto);

        FoodResponseDto findFoodInfo = resultList.get(0);
        FoodUpdateRequestDto foodUpdateRequestDto = new FoodUpdateRequestDto();
        foodUpdateRequestDto.setFoodNo(findFoodInfo.getFoodNo());
        foodUpdateRequestDto.setFoodName("탕수육");
        // when
        FoodResponseDto resultFoodInfo = foodMntService.modifyFoodInfo(foodUpdateRequestDto);
        // then
        assertThat(resultFoodInfo.getFoodName()).isEqualTo("탕수육");
    }
    
    @Test
    @DisplayName("음식노출여부 변경")
    public void modifyFoodIsShow() throws Exception {
        // given
        String searchText = "마라탕";
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setFoodName(searchText);

        List<FoodResponseDto> findList = foodMntService.findFoodMntList(foodRequestDto);

        FoodResponseDto findFoodInfo = findList.get(0);
        FoodUpdateRequestDto foodUpdateRequestDto = new FoodUpdateRequestDto();
        foodUpdateRequestDto.setFoodNo(findFoodInfo.getFoodNo());
        foodUpdateRequestDto.setIsShow('N');

        // when
        FoodResponseDto resultFoodInfo = foodMntService.modifyFoodInfo(foodUpdateRequestDto);

        // then
        assertThat(resultFoodInfo.getIsShow()).isEqualTo('N');
    }


    @Test
    @DisplayName("음식이름 정보 삭제 - isShow를 D로 한다. (실제 삭제X)")
    public void deleteFoodInfo() throws Exception {
        // given
        String searchText = "마라탕";
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setFoodName(searchText);

        List<FoodResponseDto> findList = foodMntService.findFoodMntList(foodRequestDto);
        // when
        FoodResponseDto findFoodInfo = findList.get(0);

        foodMntService.deleteFoodInfo(findFoodInfo.getFoodNo());

        // then
        FoodResponseDto findResult = foodMntService.findFoodInfoByFoodNo(findFoodInfo.getFoodNo());

        assertThat(findResult).isNull();

    }
    
}