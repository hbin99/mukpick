package com.jumpstd.mukpick.admin.dto.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CodeUpdateRequestDto {
    private String codeName;
    private String groupType;
    private long codeNo;

}
