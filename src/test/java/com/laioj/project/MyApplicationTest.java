package com.laioj.project;

import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyApplicationTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println("test");
    }

    @Test
    void testGetById(){
        User byId = userMapper.getById(3L);
        System.out.println(byId);
    }
}