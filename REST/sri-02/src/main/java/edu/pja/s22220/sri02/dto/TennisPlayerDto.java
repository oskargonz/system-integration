package edu.pja.s22220.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TennisPlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    //private LocalDate birthDate;
    private String grandStroke;
    private int pointsATP;
    private String backhand;
    private String country;
}
