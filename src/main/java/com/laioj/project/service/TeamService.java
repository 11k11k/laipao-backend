package com.laioj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laioj.project.model.entity.Team;
import com.laioj.project.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface TeamService extends IService<Team> {
    @Transactional(rollbackFor = Exception.class)
    Long addTeam(Team team, User loginUser);

//    int deleteByPrimaryKey(Long id);
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

}
