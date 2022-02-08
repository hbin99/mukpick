package com.jumpstd.mukpick.admin.service;

import com.jumpstd.mukpick.admin.dao.MemberMntDao;
import com.jumpstd.mukpick.admin.domain.MemberMntDomain;
import com.jumpstd.mukpick.admin.dto.MemberResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberMntServiceImpl implements MemberMntService{
    @Autowired
    MemberMntDao memberMntDao;

    @Override
    public List<MemberResponseDto> memberAllList() {
        List<MemberMntDomain> memberList = memberMntDao.memberAllList();

        List<MemberResponseDto> resultList = new ArrayList<>();

        for (MemberMntDomain domain : memberList) {
            resultList.add(domain.getMemberMntDto());
        }
        return resultList;
    }
}
