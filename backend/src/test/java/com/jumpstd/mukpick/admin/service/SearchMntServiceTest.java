package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.exception.NullDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayName("검색관리 Service 테스트")
@Transactional
class SearchMntServiceTest {
    @Autowired
    SearchMntService searchMntService;

    @BeforeEach
    @DisplayName("초기화 메서드 - 데이터 19개 세팅")
    public void init(){
        // 혹시 존재할 데이터를 모두 삭제
        searchMntService.deleteSearchTextAll();

        searchMntService.saveSearchText("라볶이");
        searchMntService.saveSearchText("마라탕");
        searchMntService.saveSearchText("삼계탕");
        searchMntService.saveSearchText("피자");
        searchMntService.saveSearchText("라면");
        searchMntService.saveSearchText("삼겹살");
        searchMntService.saveSearchText("돼지국밥");
        searchMntService.saveSearchText("소고기");
        searchMntService.saveSearchText("순대");
        searchMntService.saveSearchText("떡볶이");
        searchMntService.saveSearchText("김치볶음밥");
        searchMntService.saveSearchText("비빔밥");
        searchMntService.saveSearchText("김밥");
        searchMntService.saveSearchText("수육");
        searchMntService.saveSearchText("보쌈");
        searchMntService.saveSearchText("닭도리탕");
        searchMntService.saveSearchText("낙곱새");
        searchMntService.saveSearchText("전어");
        searchMntService.saveSearchText("과메기");
    }

    @Test
    @DisplayName("전체 리스트 조회")
    public void findSearchList() {
        // given
        SearchRequestDto dto = new SearchRequestDto();

        // when
        List<SearchResponseDto> searchList = searchMntService.findSearchList(dto);

        // then
        assertThat(searchList.size()).isGreaterThanOrEqualTo(19);
    }

    @Test
    @DisplayName("하나만 조회")
    public void FindSearchTextOne() {
        // given
        String searchText = "라볶이";
        // when
        SearchResponseDto searchData = searchMntService.findBySearchText("라볶이");
        // then
        assertEquals(searchData.getSearchText(),searchText);
    }

    @Test
    @DisplayName("삭제 후 NULL값 조회")
    public void deleteSearchText(){
        // given
        String searchText = "라볶이";
        SearchResponseDto searchData = searchMntService.findBySearchText(searchText);
        Long searchNo = searchData.getSearchNo();
        // when
        searchMntService.deleteSearchText(searchNo);
        // then
        SearchResponseDto resultData = searchMntService.findBySearchText(searchText);
        assertThat(resultData).isNull();
    }

    @Test
    @DisplayName("전체 삭제")
    public void deleteAll() {
        // given
        searchMntService.deleteSearchTextAll();
        // when
        SearchRequestDto dto = new SearchRequestDto();
        List<SearchResponseDto> resultData = searchMntService.findSearchList(dto);
        // then
        assertThat(resultData.size()).isZero();
    }

    @Test
    @DisplayName("유효기간 업데이트")
    public void changeValidDate() throws Exception {
        // given
        String searchText = "라볶이";
        SearchResponseDto searchData = searchMntService.findBySearchText(searchText);
        Long searchNo = searchData.getSearchNo();

        // 변경할 날짜
        OffsetDateTime date= OffsetDateTime.now().plusDays(90);
        SearchValidDateRequestDto dto = new SearchValidDateRequestDto(searchNo, date);
        // when
        searchMntService.changeValidDate(dto);
        // then
        SearchResponseDto resultData = searchMntService.findBySearchText(searchText);
        assertThat(resultData.getValidDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("[실패] 유효기간 업데이트")
    public void ChangeValidDateFail() throws Exception {
        // given
        Long tempSearchNo = 9999999999999L;
        // 변경할 날짜
        OffsetDateTime date= OffsetDateTime.now().plusDays(90);
        SearchValidDateRequestDto dto = new SearchValidDateRequestDto(tempSearchNo, date);
        // when, then
        assertThrows(NullDataException.class,() ->{
            searchMntService.changeValidDate(dto);
        });
    }

    @Test
    @DisplayName("음식관리로 데이터 전송")
    public void transferToFood() throws Exception {
        // given
        String text = "라볶이";
        SearchResponseDto searchData = searchMntService.findBySearchText(text);
        Long searchNo = searchData.getSearchNo();
        // when
        int i = searchMntService.transferToFood(searchNo);
        // then
        assertThat(i).isEqualTo(1);
    }

    @Test
    @DisplayName("[실패] 음식관리로 데이터 전송")
    public void transferToFoodFail() throws Exception {
        // given
        Long tempSearchNo = 9999999999999L;
        // when, then
        assertThrows(NullDataException.class, () -> {
            searchMntService.transferToFood(tempSearchNo);
        });
    }
}