package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.service.SearchMntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/admin/search")
@RestController
public class SearchMntAPI {
    @Autowired
    SearchMntService searchMntService;
    @GetMapping
    public ResponseEntity<List<SearchResponseDto>> saerchAllList(SearchRequestDto request){
        List<SearchResponseDto> responses = searchMntService.findSearchList(request);
        return ResponseEntity.ok(responses);
    }



}
