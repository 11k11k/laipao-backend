package com.laioj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laioj.project.model.dto.TeamQuery;
import com.laioj.project.model.entity.Team;
import com.laioj.project.model.entity.User;
import com.laioj.project.model.request.DeleteRequest;
import com.laioj.project.model.request.TeamJoinRequest;
import com.laioj.project.model.request.TeamQuitRequest;
import com.laioj.project.model.request.TeamUpdateRequest;
import com.laioj.project.model.vo.TeamUserVO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TeamService extends IService<Team> {
    @Transactional(rollbackFor = Exception.class)
    Long addTeam(Team team, User loginUser);

    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    boolean updateTeam(TeamUpdateRequest team, User loginUser);

    boolean joinTeam(TeamJoinRequest teamJoinRequest, User logininUser);

    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    boolean deleteTeam(long id, User loginUser);

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
