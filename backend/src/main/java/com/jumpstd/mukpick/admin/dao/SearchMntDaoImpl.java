package com.jumpstd.mukpick.admin.dao;

import com.jumpstd.mukpick.admin.domain.SearchMntDomain;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.mapper.SearchMntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchMntDaoImpl implements SearchMntDao{
    @Autowired
    SearchMntMapper searchMntMapper;

    @Override
    public List<SearchMntDomain> findList(SearchRequestDto searchRequestDto) {

        return searchMntMapper.findList(searchRequestDto);
    }

    @Override
    public SearchMntDomain findBySearchText(String searchText) {
        return searchMntMapper.findBySearchText(searchText);
    }

    @Override
    public int deleteOne(long searchNo) {
        return searchMntMapper.delete(searchNo);
    }

    @Override
    public int deleteAll() {
        return searchMntMapper.deleteAll();
    }

    @Override
    public int saveOne(String searchText) {
        return searchMntMapper.save(searchText);
    }

    @Override
    public int changeValidDate(SearchValidDateRequestDto dto) {
        return searchMntMapper.changeValidDate(dto);
    }

    @Override
    public SearchMntDomain findBySearchNo(Long searchNo) {
        return searchMntMapper.findBySearchNo(searchNo);
    }

    @Override
    public int transferToFood(Long searchNo) {
        return searchMntMapper.transferToFood(searchNo);
    }

    @Override
    public int countSearchList(SearchRequestDto searchRequestDTO) {
        return searchMntMapper.countSearchList(searchRequestDTO);
    }
}
