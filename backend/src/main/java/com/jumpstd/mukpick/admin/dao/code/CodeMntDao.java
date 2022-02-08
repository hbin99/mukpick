package com.jumpstd.mukpick.admin.dao.code;

import com.jumpstd.mukpick.admin.dto.code.CodeRequestDto;
import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import com.jumpstd.mukpick.admin.dto.code.CodeUpdateRequestDto;

import java.util.List;

public interface CodeMntDao {

    List<CodeResponseDto> findList(String groupType);

    List<CodeResponseDto> findByCodeDetail(String groupType);

    int update(CodeUpdateRequestDto codeUpdateRequestDto);

    int delete(Long codeNo);

    int insert(CodeRequestDto codeRequestDto);
}
