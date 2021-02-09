package com.artsmuzi.teamfinder.service.implementation;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.exception.PlayerNotFoundedException;
import com.artsmuzi.teamfinder.mapper.PlayerMapper;
import com.artsmuzi.teamfinder.model.AppUser;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;
import com.artsmuzi.teamfinder.repository.PlayerRepository;
import com.artsmuzi.teamfinder.repository.UserRepository;
import com.artsmuzi.teamfinder.service.PlayerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final PlayerMapper playerMapper;
    Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    public PlayerServiceImpl(PlayerRepository repository, UserRepository userRepository, PlayerMapper playerMapper) {
        this.playerRepository = repository;
        this.userRepository = userRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerDto getPlayerById(Long id) throws PlayerNotFoundedException {
        logger.info("Download player from repo");
        Player player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundedException("Player not found with id " + id));
        logger.info("mapping player to dto");
        return playerMapper.toDto(player);
    }

    @Override
    public PlayerDto getPlayerByUserName(String name) {
        logger.info("Download user from repo");
        AppUser appUser = userRepository
                .findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with name " + name + " not found"
                ));

        logger.info("fetching player associated with user");
        Player player = playerRepository
                .findById(appUser.getId())
                .orElseThrow(() -> new PlayerNotFoundedException(
                        "Player associated with user with id "
                                + appUser.getId() + " doesn't not exist"
                ));

        logger.info("mapping player to dto");
        return playerMapper.toDto(player);
    }

    @Override
    public PlayerDto updatePlayerByUserName(String username, PlayerPersonalInfo info) {
        AppUser appUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with name " + username + " not found"
                ));

        logger.info("fetching player associated with user");
        Player player = playerRepository
                .findById(appUser.getId())
                .orElseThrow(() -> new PlayerNotFoundedException(
                        "Player associated with user with id "
                                + appUser.getId() + " doesn't not exist"
                ));

        player.setInfo(info);

        return playerMapper.toDto(playerRepository.save(player));
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
