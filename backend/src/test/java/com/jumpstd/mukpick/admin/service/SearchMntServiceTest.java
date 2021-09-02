package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("검색관리 Service 테스트")
@Transactional
class SearchMntServiceTest {
    @Autowired
    SearchMntService searchMntService;

    @BeforeEach
    @DisplayName("초기화 메서드 - 데이터 2개 세팅")
    public void init(){
        String text1 = "라볶이";
        searchMntService.saveSearchText(text1);

        String text2 = "마라탕";
        searchMntService.saveSearchText(text2);
    }

    @Test
    @DisplayName("전체 리스트 조회")
    public void findSearchList() {
        // given
        SearchRequestDto dto = new SearchRequestDto();

        // when
        List<SearchResponseDto> searchList = searchMntService.findSearchList(dto);

        // then
        assertThat(searchList.size()).isGreaterThan(2);
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
//    void changeValidDate(SearchValidDateRequestDto dto);

    @Test
    @DisplayName("유효기간 업데이트")
    public void changeValidDate() throws Exception {
        // given
        String searchText = "라볶이";
        SearchResponseDto searchData = searchMntService.findBySearchText(searchText);
        Long searchNo = searchData.getSearchNo();

        // 변경할 날짜
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2021-12-31");

        SearchValidDateRequestDto dto = new SearchValidDateRequestDto(searchNo, date);
        // when
        searchMntService.changeValidDate(dto);
        // then
        SearchResponseDto resultData = searchMntService.findBySearchText(searchText);
        assertThat(resultData.getValidDate()).isEqualTo(date);
    }
}