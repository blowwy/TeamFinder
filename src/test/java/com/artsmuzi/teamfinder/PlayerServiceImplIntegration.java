package com.artsmuzi.teamfinder;

import com.artsmuzi.teamfinder.dto.PlayerDto;
import com.artsmuzi.teamfinder.exception.PlayerNotFoundedException;
import com.artsmuzi.teamfinder.mapper.PlayerMapper;
import com.artsmuzi.teamfinder.model.AppUser;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;
import com.artsmuzi.teamfinder.model.PlayerPosition;
import com.artsmuzi.teamfinder.repository.PlayerRepository;
import com.artsmuzi.teamfinder.repository.UserRepository;
import com.artsmuzi.teamfinder.service.PlayerService;
import com.artsmuzi.teamfinder.service.implementation.PlayerServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.AttributeOverride;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceImplIntegration {

    @MockBean
    public PlayerRepository playerRepository;

    @MockBean
    public UserRepository userRepository;

    @MockBean
    public PlayerMapper mapper;

    @Autowired
    public PlayerService playerService;

    @Test
    public void whenFindPlayerById_thenReturnPlayerWithName() {
        Player john = new Player();
        john.setId(5L);
        john.setInfo(new PlayerPersonalInfo("A", "K",
                "A", "K", PlayerPosition.ATTACKER ));
        Mockito.when(playerRepository.findById(john.getId())).thenReturn(Optional.of(john));
        PlayerDto info = new PlayerDto();
        info.setFirstName("JOHN");
        Mockito.when(mapper.toDto(john)).thenReturn(info);

        PlayerDto playerById = playerService.getPlayerById(john.getId());

        assertThat(playerById.getFirstName()).isEqualTo(info.getFirstName());
    }

    @Test
    public void whenFindPlayerByIdIsNotFound_thenThrowPlayerNotFoundException() {
        Long id = 3L;
        Mockito.when(playerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundedException.class, () -> playerService.getPlayerById(id));
    }

    @Test
    public void xx() {
        Player player = new Player();
        player.setId(2L);
        AppUser user = new AppUser();
        user.setUsername("John");
        user.setId(5L);
        user.setPlayer(player);
        PlayerDto info = new PlayerDto();
        info.setFirstName("Player");

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        Mockito.when(mapper.toDto(player)).thenReturn(info);

        assertThat(playerService.getPlayerByUserName(user.getUsername())).isEqualTo(info);

    }

}
