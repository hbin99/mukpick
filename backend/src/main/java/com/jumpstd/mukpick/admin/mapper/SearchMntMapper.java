package com.jumpstd.mukpick.admin.mapper;

import com.jumpstd.mukpick.admin.domain.SearchMntDomain;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMntMapper {
    SearchMntDomain findBySearchText(String searchText);

    SearchMntDomain findBySearchNo(Long searchNo);
    
    List<SearchMntDomain> findList(SearchRequestDto searchRequestDTO);

    int save(String searchText);

    int delete(Long searchNo);

    int deleteAll();

    int changeValidDate(SearchValidDateRequestDto searchValidDateRequestDto);
    
    int transferToFood(Long searchNo);

    int countSearchList(SearchRequestDto searchRequestDTO);
}
