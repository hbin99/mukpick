package com.jumpstd.mukpick.admin.dto.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CodeResponseDto {
    private Long codeNo;
    private String codeName;
    private String groupType;


}
