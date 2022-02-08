package com.jumpstd.mukpick.admin.mapper.code;

import com.jumpstd.mukpick.admin.dto.code.CodeRequestDto;
import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import com.jumpstd.mukpick.admin.dto.code.CodeUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CodeMntMapper {

    List<CodeResponseDto> findByCodeDetail(String groupType);

    List<CodeResponseDto> findList(String groupType);

    int update(CodeUpdateRequestDto CodeUpdateRequestDto);

    int delete(Long codeNo);

    int insert(CodeRequestDto codeRequestDto);
}
