package com.jumpstd.mukpick.admin.api;

import com.jumpstd.mukpick.admin.dto.FoodRequestDto;
import com.jumpstd.mukpick.admin.dto.FoodResponseDto;
import com.jumpstd.mukpick.admin.dto.FoodUpdateRequestDto;
import com.jumpstd.mukpick.admin.service.FoodMntService;
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
        return ResponseEntity.ok(foodMntList);
    }

    @PatchMapping("/{foodNo}")
    public ResponseEntity<FoodResponseDto> modifyFoodInfo(@PathVariable Long foodNo,@RequestBody FoodUpdateRequestDto fudto){
        fudto.setUpFoodNo(foodNo);
        System.out.println(fudto+"혀이");
        FoodResponseDto response = foodMntService.modifyFoodInfo(fudto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{foodNo}")
    public ResponseEntity deleteFoodInfo(@PathVariable Long foodNo){
        int successFlag = foodMntService.deleteFoodInfo(foodNo);
        if (successFlag == 0){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foodNo);
    }
}
