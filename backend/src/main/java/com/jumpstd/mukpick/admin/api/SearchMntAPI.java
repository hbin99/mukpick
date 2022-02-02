package com.jumpstd.mukpick.admin.api;
import com.jumpstd.mukpick.admin.dto.RequestDateDto;
import com.jumpstd.mukpick.admin.dto.SearchRequestDto;
import com.jumpstd.mukpick.admin.dto.SearchResponseDto;
import com.jumpstd.mukpick.admin.dto.SearchValidDateRequestDto;
import com.jumpstd.mukpick.admin.service.SearchMntService;
import com.jumpstd.mukpick.common.exception.AuthenticationException;
import com.jumpstd.mukpick.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<SearchResponseDto>> searchAllList(SearchRequestDto request){
        System.out.println("start and limit " + request.getStart() + ", " + request.getLimit());
        String tmpUserId = "tjdud1994"; // 임시 처리 로그인 기능 구현 시 로그인 유저 정보로 체크 예정
        List<SearchResponseDto> responses = searchMntService.findSearchList(request);
        for (SearchResponseDto response : responses) {
            System.out.println(response);
        }
        if (tmpUserId.isEmpty()) throw new AuthenticationException(ErrorCode.UNAUTHORIZED_USER); // todo: admin 유저가 아니면 해당 예외 실행으로 변경
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
        System.out.println(dateDto.getDate());
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
        searchMntService.transferToFood(searchNo);
        return ResponseEntity.ok(searchNo);
    }

    @DeleteMapping("/{searchNo}")
    public ResponseEntity deleteSearchText(@PathVariable Long searchNo){
        try{
            searchMntService.deleteSearchText(searchNo);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(searchNo);
    }
}
