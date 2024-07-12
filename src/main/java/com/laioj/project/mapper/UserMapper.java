package com.laioj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.laioj.project.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

     List<User> selectAll();




//    User getById(@Param("id") Long id);
User getById(@Param("id")Long id);


//    List<User> getByTags(@Param("tags") List<String> tags);
    List<User> getByTags(@Param("tags")List<String> tags);

//
//    Long countByUserAccount(@Param("userAccount") String userAccount);
    Long countByUserAccount(@Param("userAccount")String userAccount);


//    User selectAllByUserAccountAndUserPassword(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword);
    User selectAllByUserAccountAndUserPassword(@Param("userAccount")String userAccount,@Param("userPassword")String userpassword);


}