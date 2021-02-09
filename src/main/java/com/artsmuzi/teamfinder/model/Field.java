package com.artsmuzi.teamfinder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Embedded
    private Coordinates coordinates;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Team> teams = new ArrayList<>();

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", teams=" + teams +
                '}';
    }
}
