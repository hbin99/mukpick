package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import com.jumpstd.mukpick.admin.exception.NoValidFoodNoException;
import com.jumpstd.mukpick.admin.service.FoodMntService;
import com.jumpstd.mukpick.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/admin/food")
@RestController
public class FoodMntAPI {
    @Autowired
    FoodMntService foodMntService;

    @GetMapping
    public ResponseEntity<List<FoodResponseDto>> foodAllList(FoodRequestDto dto){
        List<FoodResponseDto> foodMntList = foodMntService.findFoodMntList(dto);
        System.out.println("헬로우");
        return ResponseEntity.ok(foodMntList);
    }

    @PatchMapping("/{foodNo}")
    public ResponseEntity<Object> modifyFoodInfo(@PathVariable Long foodNo,@RequestBody FoodUpdateRequestDto fudto){
        fudto.setUpFoodNo(foodNo);
        FoodResponseDto response = foodMntService.modifyFoodInfo(fudto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{foodNo}")
    public ResponseEntity<String> deleteFoodInfo(@PathVariable Long foodNo){
        int successFlag = foodMntService.deleteFoodInfo(foodNo);

        if (successFlag == 0){
            return ResponseEntity.badRequest().body("삭제에 실패했습니다. 유효하지 않은 음식입니다.");
        }
        return ResponseEntity.ok("삭제 성공했습니다.");
    }
}
