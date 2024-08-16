package com.laioj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laioj.project.model.entity.Team;
public interface TeamService extends IService<Team> {

    int deleteByPrimaryKey(Long id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

}
