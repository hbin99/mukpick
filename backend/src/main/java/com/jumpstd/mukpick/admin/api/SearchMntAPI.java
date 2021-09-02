package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.service.SearchMntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @PatchMapping("/{searchNo}")
    public ResponseEntity<SearchResponseDto> changeValidDate(@RequestBody SearchValidDateRequestDto request){
        SearchResponseDto response = searchMntService.changeValidDate(request);
        System.out.println(request);
        return ResponseEntity.ok(response);
    }


}
