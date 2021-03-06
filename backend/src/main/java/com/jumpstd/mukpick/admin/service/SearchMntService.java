package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;

import java.util.List;

public interface SearchMntService {

    List<SearchResponseDto> findSearchList(SearchRequestDto sdto);

    SearchResponseDto findBySearchText(String searchText);

    SearchResponseDto findBySearchNo(Long searchNo);

    void saveSearchText(String searchText);

    void deleteSearchText(Long searchNo);

    void deleteSearchTextAll();

    SearchResponseDto changeValidDate(SearchValidDateRequestDto dto);

    int transferToFood(Long searchNo);
}
