package com.artsmuzi.teamfinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlayerPersonalInfo {
    private String firstName;
    private String lastName;
    private String address;

    @Column(name = "phone")
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    private PlayerPosition position;

    @Override
    public String toString() {
        return "PlayerPersonalInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position=" + position +
                '}';
    }
}

