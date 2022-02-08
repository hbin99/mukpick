package com.jumpstd.mukpick.admin.api.code;


import com.jumpstd.mukpick.admin.dto.code.CodeResponseDto;
import com.jumpstd.mukpick.admin.service.code.CodeMntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequestMapping("/api/admin/code")
@RestController
public class CodeMntAPI {

    @Autowired
    CodeMntService codeMntService;

    @GetMapping
    public ResponseEntity<List<CodeResponseDto>> codeAllList(String groupType){
        return ResponseEntity.ok(codeMntService.findList(groupType));
    }
}
