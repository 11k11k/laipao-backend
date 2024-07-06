package com.laioj.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laioj.project.common.ErrorCode;
import com.laioj.project.exception.BusinessException;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务测试
 *
 * @author yupi
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Test
    void test() {
        System.out.println("测试");
    }

    @Test
    void testSearchUsersByTags(){
        List<String> tagNameList = Arrays.asList("java", "c++");
        List<User> userList = userService.searchUsersByTags(tagNameList);
        Assertions.assertNotNull(userList);
    }
    @Test
    void testSearchUsers(){
        List<User> users = userMapper.selectList(null);

        Assertions.assertNotNull(users);
    }
//
//    @Test
//    void testAddUser() {
//        User user = new User();
//        boolean result = userService.save(user);
//        System.out.println(user.getId());
//        Assertions.assertTrue(result);
//    }
//
    @Test
    void testUpdateUser() {
        User user = new User();
        boolean result = userService.updateById(user);
        Assertions.assertTrue(result);
    }
//
//    @Test
//    void testDeleteUser() {
//        boolean result = userService.removeById(1L);
//        Assertions.assertTrue(result);
//    }
//
    @Test
    void testGetUser() {
        User user = userService.getById(1L);
        Assertions.assertNotNull(user);
    }
//
//    @Test
//    void userRegister() {
//        String userAccount = "yupi";
//        String userPassword = "";
//        String checkPassword = "123456";
//        try {
//            long result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yupi";
//            userPassword = "123456";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu pi";
//            userPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            checkPassword = "123456789";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "dogYupi";
//            checkPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yupi";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//        } catch (Exception e) {
//
//        }
//    }
}