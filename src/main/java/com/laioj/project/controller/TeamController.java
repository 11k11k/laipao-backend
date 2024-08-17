package com.laioj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laioj.project.common.BaseResponse;
import com.laioj.project.common.ErrorCode;
import com.laioj.project.common.ResultUtils;
import com.laioj.project.exception.BusinessException;
import com.laioj.project.model.dto.TeamQuery;
import com.laioj.project.model.entity.Team;
import com.laioj.project.model.entity.User;
import com.laioj.project.model.request.TeamAddRequest;
import com.laioj.project.service.TeamService;
import com.laioj.project.service.UserService;
import com.laioj.project.service.impl.TeamServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 队伍(team)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Slf4j
public class TeamController {
    /**
     * 服务对象
     */
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    /**
     * 增加队伍
     *
     * @param teamAddRequest request
     * @return BaseResponse</>
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest request) {
        if (teamAddRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User logininUser = userService.getCurrentUser(request);
        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest, team);
        long teamId = teamService.addTeam(team, logininUser);
        return ResultUtils.success(teamId);
    }

    /**
     * 删除功能
     *
     * @param id
     * @return 返回true, boolean类型的BaseResponse
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!teamService.removeById(id)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return ResultUtils.success(true);

    }

    /**
     * 修改功能
     *
     * @param team
     * @return 成功返回true
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!teamService.updateById(team)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "修改失败");

        }
        return ResultUtils.success(true);
    }

    //查询
    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(@RequestParam long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = teamService.getById(id);
        if (team == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(team);
    }

    @GetMapping("/list")
    public BaseResponse<List<Team>> listTeams(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = new Team();
        BeanUtils.copyProperties(team, teamQuery);
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>(team);
        List<Team> list = teamService.list(teamQueryWrapper);
        return ResultUtils.success(list);
    }

    /**
     * 查询页面
     *
     * @param teamQuery
     * @return Page<Team></>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Team>> listPageTeams(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = new Team();
        BeanUtils.copyProperties(team, teamQuery);
        //分页参数有teamQuery提供
        Page<Team> page = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>(team);
        Page<Team> resultPage = teamService.page(page, queryWrapper);
        return ResultUtils.success(resultPage);
    }
}
