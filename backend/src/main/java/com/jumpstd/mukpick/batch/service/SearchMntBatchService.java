package com.jumpstd.mukpick.batch.service;

import com.jumpstd.mukpick.batch.dao.SearchMntBatchDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchMntBatchService {
    @Autowired
    SearchMntBatchDao searchMntBatchDao;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 12시에 유효기간 체크 후 삭제 실행
    public void deleteSearchText(){
        log.info("검색관리 - [유효기간 만료 삭제] 배치 실행");
        int flag = searchMntBatchDao.deleteExpiredPeriod();

        if(flag != 0){
            log.info("검색관리 - [유효기간 만료 삭제] 배치 결과 - "+ flag + "개의 데이터를 삭제했습니다.");
        }else{
            log.info("검색관리 - [유효기간 만료 삭제] 배치 결과 - 삭제한 데이터가 없습니다.");
        }
    }
}
