package com.laioj.project.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.laioj.project.common.BaseResponse;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.User;
import com.laioj.project.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest

public class UserControllerTest {


    @Resource
    private UserController userController;
    @Resource
    private RedissonClient redissonClient;
    @Test
    void testRedis() {
        System.out.println("测试");
        RList<String> test00 = redissonClient.getList("test00");
        boolean add = test00.add("测试redis");
        System.out.println("添加成功"+add);
        String s = test00.get(0);
        System.out.println("输出存入数据"+s);

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