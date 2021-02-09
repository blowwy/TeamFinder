package com.artsmuzi.teamfinder.repository;

import com.artsmuzi.teamfinder.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldRepository extends JpaRepository<Field, Long> {
    @Query("SELECT f FROM Field f LEFT JOIN FETCH f.teams WHERE f.id = (:id)")
    Field getFieldWithLoadedTeams(Long id);
}

