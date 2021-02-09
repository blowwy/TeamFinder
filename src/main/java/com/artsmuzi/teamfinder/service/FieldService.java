package com.artsmuzi.teamfinder.service;

import com.artsmuzi.teamfinder.dto.TeamDto;
import com.artsmuzi.teamfinder.model.Team;

import java.util.List;

public interface FieldService {
    List<TeamDto> getFieldTeams(Long fieldId);
    TeamDto addTeamToField(Long fieldId, Long teamId);
    TeamDto addTeamToField(Long fieldId, TeamDto teamDto);
    TeamDto deleteTeamFromField(Long fieldId, Long teamId);
}
