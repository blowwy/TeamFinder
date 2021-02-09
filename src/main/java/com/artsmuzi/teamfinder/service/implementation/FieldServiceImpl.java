package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.dto.TeamDto;
import com.artsmuzi.teamfinder.exception.PlayerNotFoundedException;
import com.artsmuzi.teamfinder.exception.TeamNotFoundedException;
import com.artsmuzi.teamfinder.mapper.TeamMapper;
import com.artsmuzi.teamfinder.model.Team;
import com.artsmuzi.teamfinder.repository.FieldRepository;
import com.artsmuzi.teamfinder.repository.TeamRepository;
import com.artsmuzi.teamfinder.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    FieldServiceImpl(FieldRepository fieldRepository, TeamRepository teamRepository, TeamMapper teamMapper) {

        this.fieldRepository = fieldRepository;
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public List<TeamDto> getFieldTeams(Long fieldId) {
        return fieldRepository
                .getFieldWithLoadedTeams(fieldId).getTeams()
                .stream().map(teamMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto addTeamToField(Long fieldId, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundedException(
                        "Team with id " + teamId + " doesn't exist"
                ));

        team.setField(fieldRepository.getOne(fieldId));
        return teamMapper.toDto(team);
    }

    @Override
    public TeamDto addTeamToField(Long fieldId, TeamDto teamDto) {
        Team entity = teamMapper.toEntity(teamDto);

        return teamMapper.toDto(teamRepository.save(entity));
    }

    @Override
    public TeamDto deleteTeamFromField(Long fieldId, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundedException(
                        "Team with id " + teamId + " doesn't exist"
                ));

        team.setField(null); // TODO think maybe fieldId == 0 is good option

        return teamMapper.toDto(team);
    }
}
