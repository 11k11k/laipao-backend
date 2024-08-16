package com.laioj.project.controller;
import com.laioj.project.model.entity.Team;
import com.laioj.project.service.impl.TeamServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 队伍(team)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/team")
public class TeamController {
/**
* 服务对象
*/
    @Autowired
    private TeamServiceImpl teamServiceImpl;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
//    @GetMapping("selectOne")
//    public Team selectOne(Integer id) {
//    return teamServiceImpl.selectByPrimaryKey(id);
//    }

}
