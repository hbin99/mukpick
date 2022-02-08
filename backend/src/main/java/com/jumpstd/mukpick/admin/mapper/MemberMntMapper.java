package com.jumpstd.mukpick.admin.mapper;

import com.jumpstd.mukpick.admin.domain.MemberMntDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMntMapper {

    List<MemberMntDomain> memberAllList();
}
