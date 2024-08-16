package com.laioj.project.controller;
import com.laioj.project.model.entity.UserTeam;
import com.laioj.project.service.impl.UserTeamServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 用户队伍关系(user_team)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/user_team")
public class UserTeamController {
/**
* 服务对象
*/
    @Autowired
    private UserTeamServiceImpl userTeamServiceImpl;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public UserTeam selectOne(Integer id) {
    return userTeamServiceImpl.getById(id);
    }

}
