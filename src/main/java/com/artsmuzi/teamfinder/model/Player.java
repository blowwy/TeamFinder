package com.artsmuzi.teamfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {
    @Id
    private Long id;

    @Embedded
    private PlayerPersonalInfo info;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", info=" + info +
                '}';
    }
}
