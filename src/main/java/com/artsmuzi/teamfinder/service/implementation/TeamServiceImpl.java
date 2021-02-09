package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.exception.PlayerNotFoundedException;
import com.artsmuzi.teamfinder.mapper.PlayerMapper;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.repository.PlayerRepository;
import com.artsmuzi.teamfinder.repository.TeamRepository;
import com.artsmuzi.teamfinder.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerDto addPlayerToTeam(Long teamId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundedException(
                        "Player with id " + playerId + " doesn't exist"
                ));
        player.setTeam(teamRepository.getOne(teamId));

        return playerMapper.toDto(player);
    }



    @Override
    public PlayerDto deletePlayerFromTeam(Long teamId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundedException(
                        "Player with id " + playerId + " doesn't exist"
                ));

        player.setTeam(null); // Seems bad man

        return playerMapper.toDto(player);
    }

    @Override
    public List<PlayerDto> getTeamPlayers(Long teamId) {
         return teamRepository
                 .getTeamWithLoadedPlayers(teamId).getPlayers()
                 .stream().map(playerMapper::toDto).filter(Objects::nonNull)
                 .collect(Collectors.toList());
    }
}
