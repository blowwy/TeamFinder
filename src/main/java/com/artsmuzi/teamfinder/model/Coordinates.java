package com.artsmuzi.teamfinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordinates {

    private Double x;
    private Double y;

    @Override
    public String toString() {
        return x + " " + y;
    }
}
