package edu.pja.s22220.sri02.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

//Adnotacja @Entity z biblioteki JPA (Java Persistence API) oznacza, że klasa reprezentuje encję mapowaną na bazę danych
@Entity
//Adnotacja @Data z biblioteki Lombok pozwoli na wygenerowanie metod get i set dla wszystkich prywatnych atrybutów klasy, co nieco zmniejszy ilość wymaganego kodu i zwiększy jego przejrzystość
@Data
//Adnotacje @NoArgsConstructor i @AllArgsConstructor prowadzą do wygenerowania odpowiednio konstruktora bezargumentowego (który jest
// wymagany dla encji JPA) oraz konstruktora z wszystkimi atrybutami (który nieco ułatwi tworzenie nowych obiektów)
@NoArgsConstructor
@AllArgsConstructor

public class TennisPlayer {
    //@Id wskazuje na klucz główny tabeli,@GeneratedValue - automatyczny sposób generowania atrybutu
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Poniższe atrybuty będą kolumnami w bazie danych
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String grandStroke;
    private int pointsATP;
    private String backhand;
    private String country;

}
