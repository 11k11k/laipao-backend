package com.laioj.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laioj.project.common.BaseResponse;
import com.laioj.project.controller.UserController;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.Tag;
import com.laioj.project.model.entity.User;
import com.laioj.project.service.TagService;
import com.laioj.project.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    @Test
    void getUserTest() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(userQueryWrapper);
        assertNotNull(users);
    }

    @Test
    void selectOne() {
        String userAccount = "yupioj";
        String encryptPassword = "6ddae35138e69874946234d2fe11ad75";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);

        User user = userMapper.selectOne(queryWrapper);
//        User user = userMapper.selectAllByUserAccountAndUserPassword(userAccount, encryptPassword);

        System.out.println(user);
    }

    @Test
    void save() {
        String userAccount = "yupioj22";
        String encryptPassword = "123456789";
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        if (!ObjectUtils.isEmpty(user)){
            boolean save = userService.saveOrUpdate(user);
            System.out.println(save);
        }
        else{
            System.out.println(user);
            System.out.println("为空");
        }
    }
    @Test
    void getUserById(){

        User user = userMapper.getById(1L);
        System.out.println(user);

    }

    @Test
    void insert() {
        User user = new User();
        user.setUserAccount("laipaopao");
        user.setUserPassword("asdfa");
        user.setPlanetCode("asdf");
        int insert = userMapper.insertSelective(user);
        System.out.println(user.getId());
        User user1 = userMapper.selectById(user.getId());
        System.out.println(user1);
        System.out.println(insert);
    }
    @Test
    void page(){
        int pageNum=1;
        int pageSize=10;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> userPage = userService.page(new Page<>(pageNum,pageSize),queryWrapper);
        System.out.println(userPage);
    }
}