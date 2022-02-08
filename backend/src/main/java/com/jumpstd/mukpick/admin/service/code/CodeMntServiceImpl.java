package com.jumpstd.mukpick.admin.service.code;

import com.jumpstd.mukpick.admin.dao.code.CodeMntDao;
import com.jumpstd.mukpick.admin.domain.code.CodeMntDomain;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.code.CodeRequestDto;
import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import com.jumpstd.mukpick.admin.dto.code.CodeUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CodeMntServiceImpl implements CodeMntService{

    @Autowired
    CodeMntDao codeMntDao;

    @Override
    public List<CodeResponseDto> findList(String groupType) {
        return codeMntDao.findList(groupType);
    }
    @Override
    public List<CodeResponseDto> findByCodeDetail(String groupType) {
        return codeMntDao.findByCodeDetail(groupType);
    }

    @Override
    public int update(CodeUpdateRequestDto codeUpdateRequestDto){

        return codeMntDao.update(codeUpdateRequestDto);
    }

    @Override
    public int delete(Long codeNo){
        return codeMntDao.delete(codeNo);
    }

    @Override
    public int insert(CodeRequestDto codeRequestDto){
        return codeMntDao.insert(codeRequestDto);
    }
}
