package com.artsmuzi.teamfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto {
    private Long id;
    private double x;
    private double y;
    private String name;
    private String address;
}
