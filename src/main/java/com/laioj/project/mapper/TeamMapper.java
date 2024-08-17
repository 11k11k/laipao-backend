package com.laioj.project.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laioj.project.model.entity.Team;
import org.apache.ibatis.annotations.Param;

public interface TeamMapper extends BaseMapper<Team> {

    int insertList(@Param("list") List<Team> list);


    List<Team> selectAll();


}