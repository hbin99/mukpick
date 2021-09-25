package com.jumpstd.mukpick.batch.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchMntBatchMapper {
    int deleteExpiredPeriod();
}
