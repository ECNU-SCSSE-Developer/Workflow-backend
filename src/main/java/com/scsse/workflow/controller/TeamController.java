package com.scsse.workflow.controller;

import com.scsse.workflow.service.TeamService;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.DAOUtil.UserUtil;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 3:11 下午
 */
@RestController
public class TeamController {

    private final UserUtil userUtil;

    private final TeamService teamService;

    private final UserService userService;

    @Autowired
    public TeamController(UserUtil userUtil, TeamService teamService, UserService userService) {
        this.userUtil = userUtil;
        this.teamService = teamService;
        this.userService = userService;
    }

    /**
     * 获取我加入的团队
     *
     * @return List{TeamDto}
     */
    @GetMapping("/team/joinedTeam")
    public Result getJoinTeam() {
        return ResultUtil.success(userService.findJoinedTeam(userUtil.getLoginUser()));
    }


}
