package com.laioj.project.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laioj.project.model.entity.Team;
import org.apache.ibatis.annotations.Param;

public interface TeamMapper extends BaseMapper<Team> {
//    int deleteByPrimaryKey(@Param("id") Long id);
//
//    int insert(Team record);
//
//    int insertSelective(Team record);
//
//    Team selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(Team record);
//
//    int updateByPrimaryKey(Team record);
    List<Team> selectAll();


}