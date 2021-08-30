package com.jumpstd.mukpick.member.dao;

import com.jumpstd.mukpick.member.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginDaoTest {
    @Autowired
    MemberMapper mapper;
    @Test
    public void testest(){
        System.out.println(mapper.getDate());



    }
}