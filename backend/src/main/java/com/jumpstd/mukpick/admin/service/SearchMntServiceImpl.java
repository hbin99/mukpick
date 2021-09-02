package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dao.SearchMntDao;
import com.jumpstd.mukpick.admin.domain.SearchMntDomain;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchMntServiceImpl implements SearchMntService{
    @Autowired
    SearchMntDao searchMntDao;

    @Override
    public List<SearchResponseDto> findSearchList(SearchRequestDto sdto) {
        List<SearchMntDomain> searchList = searchMntDao.findList(sdto);

        List<SearchResponseDto> resultList = new ArrayList();

        for (SearchMntDomain domain:searchList) {
            resultList.add(domain.getSearchMntDto());
        }

        return resultList;
    }

    @Override
    public SearchResponseDto findBySearchText(String searchText) {
        SearchMntDomain searchData = searchMntDao.findBySearchText(searchText);
        if(searchData != null){
            return searchData.getSearchMntDto();
        }
        return null;
    }

    @Override
    public SearchResponseDto findBySearchNo(Long searchNo) {
        SearchMntDomain searchData = searchMntDao.findBySearchNo(searchNo);
        if(searchData != null){
            return searchData.getSearchMntDto();
        }
        return null;
    }

    @Override
    public void saveSearchText(String searchText) {
        searchMntDao.saveOne(searchText);
    }

    @Override
    public void deleteSearchText(Long searchNo) {
        searchMntDao.deleteOne(searchNo);
    }

    @Override
    public void deleteSearchTextAll() {
        searchMntDao.deleteAll();
    }

    @Override
    public SearchResponseDto changeValidDate(SearchValidDateRequestDto dto) {
        searchMntDao.changeValidDate(dto);
        SearchMntDomain searchData = searchMntDao.findBySearchNo(dto.getSearchNo());
        if(searchData == null){
            return null;
        }
        return searchData.getSearchMntDto();

    }
}
