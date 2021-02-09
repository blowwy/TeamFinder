package com.artsmuzi.teamfinder.service;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;

import java.util.List;

public interface PlayerService {
    PlayerDto getPlayerById(Long id);
    PlayerDto getPlayerByUserName(String name);
    PlayerDto updatePlayerByUserName(String username, PlayerPersonalInfo info);
    List<PlayerDto> getAllPlayers();
}
