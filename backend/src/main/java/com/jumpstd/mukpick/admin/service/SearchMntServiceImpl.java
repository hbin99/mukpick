package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dao.SearchMntDao;
import com.jumpstd.mukpick.admin.domain.SearchMntDomain;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.exception.NullDataException;
import com.jumpstd.mukpick.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchMntServiceImpl implements SearchMntService{
    @Autowired
    SearchMntDao searchMntDao;

    @Override
    public List<SearchResponseDto> findSearchList(SearchRequestDto requestDto) {


        List<SearchMntDomain> searchList = searchMntDao.findList(requestDto);
        List<SearchResponseDto> resultList = new ArrayList();
        Boolean hasNext = hasNext(requestDto);

        for (SearchMntDomain domain:searchList) {
                resultList.add(domain.getSearchMntDto());
                resultList.get(resultList.size()-1).changeHasNext(hasNext);
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
        if (findBySearchText(searchText) == null){
            searchMntDao.saveOne(searchText);
        }
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

        int successFlag = searchMntDao.changeValidDate(dto);
        if(successFlag == 0) throw new NullDataException(ErrorCode.NULL_DATA);

        SearchMntDomain searchData = searchMntDao.findBySearchNo(dto.getSearchNo());

        return searchData.getSearchMntDto();

    }

    @Override
    public int transferToFood(Long searchNo) {
        SearchMntDomain searchData = searchMntDao.findBySearchNo(searchNo);

        if(searchData == null) throw new NullDataException(ErrorCode.NULL_DATA);

        int flag = searchMntDao.transferToFood(searchNo);
        searchMntDao.deleteOne(searchNo);
        return flag;
    }

    public Boolean hasNext(SearchRequestDto searchRequestDTO){
        int totalCnt = searchMntDao.countSearchList(searchRequestDTO);
        if(searchRequestDTO.getStart() + searchRequestDTO.getLimit() < totalCnt){
            return true;
        }
        return false;
    }
}
