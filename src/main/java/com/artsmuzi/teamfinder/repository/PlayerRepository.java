package com.artsmuzi.teamfinder.repository;

import com.artsmuzi.teamfinder.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
