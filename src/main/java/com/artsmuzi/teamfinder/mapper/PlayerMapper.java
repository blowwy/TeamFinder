package com.artsmuzi.teamfinder.mapper;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PlayerMapper {

    @Autowired
    private ModelMapper mapper;

    public PlayerDto toDto(Player player) {
        return Objects.isNull(player) ? null : mapper.map(player, PlayerDto.class);
    }

    public Player toEntity(PlayerDto info) {
        return Objects.isNull(info) ? null : mapper.map(info, Player.class);
    }

}
