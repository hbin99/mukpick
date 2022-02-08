package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.MemberResponseDto;
import com.jumpstd.mukpick.admin.service.MemberMntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/members")
public class MemberMntAPI {
    @Autowired
    MemberMntService memberMntService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> memberAllList() {

        List<MemberResponseDto> response = memberMntService.memberAllList();
        System.out.println("회원 조회");
        return ResponseEntity.ok(response);
    }
}
