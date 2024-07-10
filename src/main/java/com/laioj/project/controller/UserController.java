package com.laioj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laioj.project.common.BaseResponse;
import com.laioj.project.common.ErrorCode;
import com.laioj.project.common.ResultUtils;
import com.laioj.project.exception.BusinessException;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.model.entity.User;
import com.laioj.project.model.request.UserLoginRequest;
import com.laioj.project.model.request.UserRegisterRequest;
import com.laioj.project.service.UserService;
import com.laioj.project.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

import static com.laioj.project.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户(user)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"})
public class UserController {
    /**
     * ~
     * 服务对象
     */
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public User selectOne(Integer id) {
        User user = userMapper.getById(3L);

        return user;
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUserByTag(@RequestParam(required = false) List<String> tagsList) {
        if (CollectionUtils.isEmpty(tagsList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userServiceImpl.searchUsersByTags(tagsList);
        return ResultUtils.success(userList);
    }

    //登录
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest,
                                    HttpServletRequest httpServletRequest) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtils.success(user);
    }
    //注册
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }
//   获取当前用户信息
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest httpServletRequest ) {
        Object userObj = httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        System.out.println(userObj);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
//        User user = userService.getById(currentUser.getId());
        User user = userMapper.getById(currentUser.getId());
        System.out.println(user);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    //修改用户信息
    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest httpServletRequest) {
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User currentUser = userService.getCurrentUser(httpServletRequest);
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }

        int result = userService.updateByPrimaryKeySelective(user, currentUser);

        return ResultUtils.success(result);
    }

}
