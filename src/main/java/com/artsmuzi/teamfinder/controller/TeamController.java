package com.artsmuzi.teamfinder.controller;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/team")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/{id}/players")
    public List<PlayerDto> getPlayers(@PathVariable("id") Long id) {
        return teamService.getTeamPlayers(id);
    }

    @PostMapping(value = "/{teamId}/player/{playerId}")
    public PlayerDto addPlayer(@PathVariable("teamId") Long teamId,
                               @PathVariable("playerId") Long playerId) {
        return teamService.addPlayerToTeam(teamId, playerId);
    }

    @DeleteMapping(value = "/{teamId}/player/{playerId}")
    public PlayerDto deletePlayer(@PathVariable("teamId") Long teamId,
                               @PathVariable("playerId") Long playerId) {
        return teamService.deletePlayerFromTeam(teamId,playerId);
    }



}
