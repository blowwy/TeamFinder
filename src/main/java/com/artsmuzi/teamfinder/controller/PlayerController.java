package com.artsmuzi.teamfinder.controller;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;
import com.artsmuzi.teamfinder.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/player")
@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/{id}")
    public PlayerDto getPlayer(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping(value = "")
    public PlayerDto getCurrentPlayer(Authentication authentication) {
        return playerService.getPlayerByUserName(authentication.getName());
    }

    @PostMapping(value = "")
    public PlayerDto updateCurrentPlayerInfo(@RequestBody PlayerPersonalInfo info,
                                             Authentication authentication) {
        return playerService.updatePlayerByUserName(authentication.getName(), info );
    }

    @GetMapping(value = "/get-players")
    public List<PlayerDto> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
