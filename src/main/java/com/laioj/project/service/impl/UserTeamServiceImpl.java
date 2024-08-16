package com.laioj.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.laioj.project.model.entity.UserTeam;
import com.laioj.project.mapper.UserTeamMapper;
import com.laioj.project.service.UserTeamService;
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam> implements UserTeamService{

}
