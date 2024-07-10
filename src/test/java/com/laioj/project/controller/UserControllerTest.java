package com.laioj.project.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.laioj.project.common.BaseResponse;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.User;
import com.laioj.project.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest

public class UserControllerTest {
    @Resource
    private UserServiceImpl userServiceImpl;

    @Resource
    private UserController userController;
    @Test
    void testSelectOne() {
        User user = userServiceImpl.selectByPrimaryKey(3L);
       Assert.notNull(user);
    }

    @Test
    void searchByTag() {
        List<String> list = Arrays.asList("java", "c++");
        BaseResponse<List<User>> userBaseResponse = userController.searchUserByTag(list);
        Assert.notNull(userBaseResponse);
    }

    @Test
    void userLogin() {

    }

    @Test
    void getCurrentUser() {

    }
}