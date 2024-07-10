package com.laioj.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import com.laioj.project.model.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    User getById(@Param("id") Long id);

    List<User> getByTags(@Param("tags") List<String> tags);
    Long countByUserAccount(@Param("userAccount")String userAccount);



}