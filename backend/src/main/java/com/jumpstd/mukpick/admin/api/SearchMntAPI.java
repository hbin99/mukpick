package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.RequestDateDto;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.service.SearchMntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/api/admin/search")
@RestController
public class SearchMntAPI {
    @Autowired
    SearchMntService searchMntService;

    /**
     * 검색 관리 리스트 조회
     * 검색조건 파라미터가 존재하지 않을 시 전체 리스트 조회
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SearchResponseDto>> saerchAllList(SearchRequestDto request){
        List<SearchResponseDto> responses = searchMntService.findSearchList(request);

        return ResponseEntity.ok(responses);
    }

    /**
     * 검색어 번호로 조회
     * 검색어 하나에 대한 정보를 조회할 때 사용
     * @param searchNo
     * @return
     */
    @GetMapping("/{searchNo}")
    public ResponseEntity<SearchResponseDto> searchOne(@PathVariable Long searchNo){
        SearchResponseDto searchData = searchMntService.findBySearchNo(searchNo);
        return ResponseEntity.ok(searchData);
    }

    /**
     * 유효기간 수정
     * 검색어에 대한 유효기간을 수정할 때 사용
     * @param searchNo
     * @param dateDto
     * @return
     */
    @PatchMapping("/{searchNo}")
    public ResponseEntity<SearchResponseDto> changeValidDate(@PathVariable Long searchNo,
                                                             @RequestBody RequestDateDto dateDto){
        SearchValidDateRequestDto request = new SearchValidDateRequestDto(searchNo, dateDto.getDate());
        SearchResponseDto searchResponseDto = searchMntService.changeValidDate(request);
        return ResponseEntity.ok(searchResponseDto);
    }

    /**
     * 검색관리테이블에서 음식 테이블로 보내준 뒤 검색관리 테이블에서는 해당 검색어를 삭제한다.
     * @param searchNo
     * @return
     */
    @PostMapping("/{searchNo}")
    public ResponseEntity transferToFood(@PathVariable Long searchNo){
        int flag = searchMntService.transferToFood(searchNo);
        return ResponseEntity.ok(flag);
    }
}
