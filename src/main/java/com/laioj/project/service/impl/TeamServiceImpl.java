package com.laioj.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laioj.project.service.UserService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.laioj.project.mapper.TeamMapper;
import com.laioj.project.model.entity.Team;
import com.laioj.project.service.TeamService;
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService{

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return teamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Team record) {
        return teamMapper.insert(record);
    }

    @Override
    public int insertSelective(Team record) {
        return teamMapper.insertSelective(record);
    }

    @Override
    public Team selectByPrimaryKey(Long id) {
        return teamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Team record) {
        return teamMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Team record) {
        return teamMapper.updateByPrimaryKey(record);
    }

}
