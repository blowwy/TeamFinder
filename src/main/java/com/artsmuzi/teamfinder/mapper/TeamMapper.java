package com.artsmuzi.teamfinder.mapper;

import com.artsmuzi.teamfinder.dto.TeamDto;
import com.artsmuzi.teamfinder.model.Team;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeamMapper {
    @Autowired
    ModelMapper mapper;

    public TeamDto toDto(Team team) {
        return Objects.isNull(team) ?  null : mapper.map(team, TeamDto.class);
    }

    public Team toEntity(TeamDto teamDto) {
        return Objects.isNull(teamDto) ? null : mapper.map(teamDto, Team.class);
    }
}
