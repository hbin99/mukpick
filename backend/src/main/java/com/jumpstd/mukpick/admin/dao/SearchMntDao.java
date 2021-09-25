package com.jumpstd.mukpick.admin.dao;

import com.jumpstd.mukpick.admin.domain.SearchMntDomain;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;

import java.util.List;

public interface SearchMntDao {
    List<SearchMntDomain> findList(SearchRequestDto searchRequestDto);

    SearchMntDomain findBySearchText(String searchText);

    SearchMntDomain findBySearchNo(Long searchNo);


    int deleteOne(long searchNo);

    int deleteAll();

    int saveOne(String searchText);

    int changeValidDate(SearchValidDateRequestDto dto);

    int transferToFood(Long searchNo);
}
