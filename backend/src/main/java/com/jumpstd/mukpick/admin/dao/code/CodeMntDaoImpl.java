package com.jumpstd.mukpick.admin.dao.code;

import com.jumpstd.mukpick.admin.dto.code.CodeRequestDto;
import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import com.jumpstd.mukpick.admin.dto.code.CodeUpdateRequestDto;
import com.jumpstd.mukpick.admin.mapper.code.CodeMntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CodeMntDaoImpl implements CodeMntDao{
    @Autowired
    CodeMntMapper codeMntMapper;

    @Override
    public List<CodeResponseDto> findList(String groupType) {
        return codeMntMapper.findList(groupType);
    }
    @Override
    public List<CodeResponseDto> findByCodeDetail(String groupType) {
        return codeMntMapper.findByCodeDetail(groupType);
    }

    @Override
    public int update(CodeUpdateRequestDto codeUpdateRequestDto){
        return codeMntMapper.update(codeUpdateRequestDto);
    }

    @Override
    public int delete(Long codeNo){
        return codeMntMapper.delete(codeNo);
    }


    @Override
    public int insert(CodeRequestDto codeRequestDto){
        return codeMntMapper.insert(codeRequestDto);
    }

}
