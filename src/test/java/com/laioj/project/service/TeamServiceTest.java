package com.laioj.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laioj.project.model.dto.TeamQuery;
import com.laioj.project.model.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamServiceTest {
    @Autowired
    private TeamService teamService;
    @Test
    public void test() {
        int pageNum=1;
        int pageSize=10;
        Team team = new Team();

        Page<Team> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>(team);
        Page<Team> resultPage = teamService.page(page, teamQueryWrapper);
        System.out.println("resultPage"+resultPage);
    }
}
