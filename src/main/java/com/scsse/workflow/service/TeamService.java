package com.scsse.workflow.service;

import com.scsse.workflow.entity.dto.TeamDto;
import com.scsse.workflow.entity.model.Team;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 6:52 下午
 */
public interface TeamService {

    TeamDto getTeam(Integer teamId);

    TeamDto createTeam(Team team);

    TeamDto updateTeam(Team team) throws Exception;

    void deleteTeam(Integer teamId);
}
