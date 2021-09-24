package com.jumpstd.mukpick.batch.dao;

import com.jumpstd.mukpick.batch.mapper.SearchMntBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchMntBatchDaoImpl implements SearchMntBatchDao{
    @Autowired
    SearchMntBatchMapper searchMntBatchMapper;

    @Override
    public int deleteExpiredPeriod() {
        return searchMntBatchMapper.deleteExpiredPeriod();
    }
}
