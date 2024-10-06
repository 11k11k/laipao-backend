package com.laioj.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laioj.project.common.ErrorCode;
import com.laioj.project.constant.UserConstant;
import com.laioj.project.exception.BusinessException;
import com.laioj.project.model.vo.UserVO;
import com.laioj.project.utils.AlgorithmUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.laioj.project.model.entity.User;
import com.laioj.project.mapper.UserMapper;
import com.laioj.project.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.laioj.project.constant.UserConstant.USER_LOGIN_STATE;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "laipao";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
//        Long l = userMapper.countByUserAccount(userAccount);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
//        if (l > 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
//        }
//        // 星球编号不能重复
//        queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("planetCode", planetCode);
//        count = userMapper.selectCount(queryWrapper);
//        if (count > 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编号重复");
//        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);

        log.info("打印User",user);
        System.out.println(user);
//        boolean saveResult = this.save(user);
//
//        if (!saveResult) {
//            return -1;
//        }
        int insert = userMapper.insertSelective(user);

        System.out.println(insert);
        log.info(String.valueOf(user));
        if (insert < 0) {
            return -1;
        }
        return user.getId();
    }


    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
//         查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);

        User user = userMapper.selectOne(queryWrapper);
//        User user = userMapper.selectAllByUserAccountAndUserPassword(userAccount, encryptPassword);

        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }
        // 用户不存在
//        if (user == null) {
//            log.info("user login failed, userAccount cannot match userPassword");
//            return null;
//        }
        // 3. 用户脱敏
        User safetyUser = getSafetyUser(user);
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        //展示使用0来代替UserConstant.ADMIN_ROLE
//
        return user != null && Objects.equals(user.getUserRole(), 0);
    }

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
    @Override
    public boolean isAdmin(User loginUser) {
        // todo 这里使用1来代替UserConstant.ADMIN_ROLE
//        这里1代表管理员，说明是管理员返回true
        return loginUser != null && Objects.equals(loginUser.getUserRole(), 1);
    }


    @Override
    public int updateByPrimaryKeySelective(User user, User loginUser) {
//       获取前端获取到的id,判断参数是否正确
        long userId = user.getId();
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // todo 补充校验，如果用户没有传任何要更新的值，就直接报错，不用执行 update 语句


        // 如果是管理员，允许更新任意用户
        // 如果不是管理员，只允许更新当前（自己的）信息
        if (!isAdmin(loginUser) && userId != loginUser.getId()) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        User oldUser = userMapper.selectById(userId);
        if (oldUser == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return userMapper.updateById(user);
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj == null) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        return (User) userObj;
    }

    @Override
    public List<User> searchUsersByTags(List<String> tagNameList) {
//        判断是否为null或则是否有空白字符
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.last("limit 50");
        //拼接and 查询
        for (String tagName : tagNameList) {
            userQueryWrapper = userQueryWrapper.like("tags", tagName);
        }
//        List<User> userList = userMapper.selectList(userQueryWrapper);
        List<User> userList = this.list(userQueryWrapper);
//        List<User> userList = userMapper.getByTags(tagNameList);
        if (CollectionUtils.isEmpty(userList)) {
            System.out.println("为空");
        }
        List<User> collect = userList.stream().map(this::getSafetyUser).collect(Collectors.toList());
        System.out.println(collect);

        return collect;

////        List<User> userList = userMapper.selectList(queryWrapper);
////        List<User> userList = userMapper.selectAll();
//        Gson gson = new Gson();
//        //2.在内存中判断是否包含要求的标签
//        return userList.stream().filter(user -> {
//            String tagsStr = user.getTags();
//            Set<String> temTagNameSet = gson.fromJson(tagsStr, new TypeToken<Set<String>>() {
//            }.getType());
//            for (String tagName : tagNameList) {
//                if (!temTagNameSet.contains(tagName)) {
//                    return false;
//                }
//            }
//            return true;
//        }).map(this::getSafetyUser).collect(Collectors.toList());

//        if (CollectionUtils.isEmpty(tagNameList)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 1. 先查询所有用户
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        List<User> userList = userMapper.selectList(queryWrapper);
//        log.info("userList size:" + userList);
//        Gson gson = new Gson();
//        // 2. 在内存中判断是否包含要求的标签
//        return userList.stream().filter(user -> {
//            String tagsStr = user.getTags();
//            log.info(tagsStr);
//            Set<String> tempTagNameSet = gson.fromJson(tagsStr, new TypeToken<Set<String>>()
//            {}.getType());
//            tempTagNameSet = Optional.ofNullable(tempTagNameSet).orElse(new HashSet<>());
//            for (String tagName : tagNameList) {
//                if (!tempTagNameSet.contains(tagName)) {
//                    return false;
//                }
//            }
//            return true;
//        }).map(this::getSafetyUser).collect(Collectors.toList());
    }

    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
//        safetyUser.setUserprofile(originUser.getUserProfile());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setTags(originUser.getTags());
        return safetyUser;
    }

    @Override
    public List<User> matchUsers(long num, User loginUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","tags");
        queryWrapper.isNotNull("tags");
//        先读取20条测试
        queryWrapper.last("limit 20");
//        List<User> list = this.list(userQueryWrapper);
//       查询用户数据
        List<User> userList = this.list(queryWrapper);
//       获取登录用户标签
        String tags = loginUser.getTags();
        Gson gson = new Gson();
//       转换成list集合
        List<String> tagList = gson.fromJson(tags, new TypeToken<List<String>>() {
        }.getType());
//       [java]
        System.out.println(tagList);
        // 用户列表的下标 => 相似度
        List<Pair<User, Long>> list = new ArrayList<>();
        for (int i = 0; i <userList.size(); i++) {
            User user = userList.get(i);
            String userTags = user.getTags();
            //如果没有标签或则当前数据的用户id和登录用户ID相等，这跳过这个循环接着下一次循环
            if (StringUtils.isBlank(userTags) || Objects.equals(user.getId(), loginUser.getId())){
                continue;
            }
//            将用户标签转换成list列表
            List<String> userTagList = gson.fromJson(userTags, new TypeToken<List<String>>() {
            }.getType());
//            userTagList:[java]
            System.out.println("userTagList:"+userTagList);
            //计算分数,判断相似度，越大越不像
            long distance = AlgorithmUtils.minDistance(tagList, userTagList);
            System.out.println("分数："+distance);
//            将集合加入到pair中
            list.add(new Pair<>(user, distance));
        }
        // 按编辑距离由小到大排序
        List<Pair<User, Long>> topUserPairList = list.stream()
                .sorted((a, b) -> (int) (a.getValue() - b.getValue()))
                .limit(num)
                .collect(Collectors.toList());
        // 原本顺序的 userId 列表
        List<Long> userIdList = topUserPairList
                        .stream()
                        .map(pair -> pair.getKey().getId())
                        .collect(Collectors.toList());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id", userIdList);
        // 1, 3, 2
        // User1、User2、User3
        // 1 => User1, 2 => User2, 3 => User3
//        使用Map集合来根据userId进行排序
//        通过id获取到对应的User对象，存进新的集合中去，就可以完成排序
        Map<Long, List<User>> userIdUserListMap = this.list(userQueryWrapper)
                .stream()
                .map(user -> getSafetyUser(user))
                .collect(Collectors.groupingBy(User::getId));
        List<User> finalUserList = new ArrayList<>();
//        遍历原本顺讯的userId
        for (Long userId : userIdList) {
//            根据这个原本正常顺序的id存进去这个列表中去
            finalUserList.add(userIdUserListMap.get(userId).get(0));
        }
        return finalUserList;

    }

}
