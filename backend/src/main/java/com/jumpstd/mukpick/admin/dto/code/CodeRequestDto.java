package com.jumpstd.mukpick.admin.dto.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CodeRequestDto {
    private String codeName;
    private String groupType;

}
