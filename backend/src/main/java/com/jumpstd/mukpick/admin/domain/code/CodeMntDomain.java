package com.jumpstd.mukpick.admin.domain.code;

import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public class CodeMntDomain {
    private Long codeNo;
    private String codeName;
    private String groupType;

    public CodeResponseDto getCodeMntDto(){
        return new CodeResponseDto(codeNo,codeName,groupType);
    }
}
