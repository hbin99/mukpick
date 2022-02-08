package com.jumpstd.mukpick.admin.dao;

import com.jumpstd.mukpick.admin.domain.MemberMntDomain;
import com.jumpstd.mukpick.admin.mapper.MemberMntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMntDaoImpl implements MemberMntDao{
    @Autowired
    MemberMntMapper memberMntMapper;

    @Override
    public List<MemberMntDomain> memberAllList() {

        return memberMntMapper.memberAllList();
    }
}
