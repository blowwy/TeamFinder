package com.artsmuzi.teamfinder.repository;

import com.artsmuzi.teamfinder.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t LEFT JOIN FETCH t.players WHERE t.id = (:id)")
    Team getTeamWithLoadedPlayers(Long id);
}
