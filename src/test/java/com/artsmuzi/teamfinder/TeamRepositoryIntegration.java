package com.artsmuzi.teamfinder;

import com.artsmuzi.teamfinder.config.WebConfig;
import com.artsmuzi.teamfinder.config.WebSecurityConfig;
import com.artsmuzi.teamfinder.exception.TeamNotFoundedException;
import com.artsmuzi.teamfinder.model.Player;
import com.artsmuzi.teamfinder.model.PlayerPersonalInfo;
import com.artsmuzi.teamfinder.model.Team;
import com.artsmuzi.teamfinder.repository.TeamRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
class TeamRepositoryIntegration {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindById_thenReturnTeam() {
        Team team = new Team();
        team.setName("TEAM");
        entityManager.persist(team);
        entityManager.flush();

        Team found = teamRepository
                .findById(team.getId()).orElseThrow(()->new TeamNotFoundedException("AAA"));

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(team.getName());
    }

    @Test
    public void whenFindTeamPlayersById_thenReturnTeamPlayers() {
        Player player = new Player();

        Team team = new Team();
        player.setTeam(team);

        entityManager.persist(team);
        entityManager.persist(player);
        entityManager.flush();
        entityManager.clear();

        Team found = teamRepository.getTeamWithLoadedPlayers(team.getId());

        assertThat(Hibernate.isInitialized(found.getPlayers())).isTrue();
        assertThat(found).isNotNull();
        assertThat(found.getPlayers().size()).isEqualTo(1);
        assertThat(found.getPlayers().get(0).getId()).isEqualTo(player.getId());
    }

    @Test
    public void playersNotEagerlyFetched() {
        Player player = new Player();

        Team team = new Team();
        player.setTeam(team);

        entityManager.persist(team);
        entityManager.persist(player);
        entityManager.flush();
        entityManager.clear();

        Team found = teamRepository.findById(team.getId()).get();

        assertThat(Hibernate.isInitialized(found.getPlayers())).isFalse();
        assertThat(found.getPlayers().size()).isEqualTo(1);
    }
}
