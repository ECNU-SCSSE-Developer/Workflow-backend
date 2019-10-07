package com.scsse.workflow.service.impl;

import com.scsse.workflow.constant.ErrorMessage;
import com.scsse.workflow.entity.dto.TeamDto;
import com.scsse.workflow.entity.model.Team;
import com.scsse.workflow.handler.WrongUsageException;
import com.scsse.workflow.repository.TeamRepository;
import com.scsse.workflow.service.TeamService;
import com.scsse.workflow.util.DAOUtil.DtoTransferHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 6:52 下午
 */

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final DtoTransferHelper dtoTransferHelper;

    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, DtoTransferHelper dtoTransferHelper, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.dtoTransferHelper = dtoTransferHelper;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamDto getTeam(Integer teamId) {
        Optional<Team> result = teamRepository.findById(teamId);
        return result.map(dtoTransferHelper::transferToTeamDto).orElse(null);
    }

    @Override
    public TeamDto createTeam(Team team) {
        return dtoTransferHelper.transferToTeamDto(teamRepository.save(team));
    }

    @Override
    public TeamDto updateTeam(Team team) throws Exception {
        Optional<Team> result = teamRepository.findById(team.getTeamId());
        if (result.isPresent()) {
            Team oldTeam = result.get();
            modelMapper.map(team,oldTeam);
            return dtoTransferHelper.transferToTeamDto(teamRepository.save(oldTeam));
        } else {
            throw new WrongUsageException(ErrorMessage.UPDATE_ENTITY_NOT_FOUND);
        }
    }

    @Override
    public void deleteTeam(Integer teamId) {
        teamRepository.deleteById(teamId);
    }
}
