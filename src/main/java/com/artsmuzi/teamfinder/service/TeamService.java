package com.artsmuzi.teamfinder.service;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.model.Player;

import java.util.List;

public interface TeamService {
    PlayerDto addPlayerToTeam(Long teamId, Long playerId);
    PlayerDto deletePlayerFromTeam(Long teamId, Long playerId);
    List<PlayerDto> getTeamPlayers(Long teamId);

}
