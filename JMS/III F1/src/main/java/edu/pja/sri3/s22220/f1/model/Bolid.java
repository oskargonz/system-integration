package edu.pja.sri3.s22220.f1.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bolid {
    private static double engineTemperatureIndex = 95.0;
    public static double newEngineTemperature() {
        return engineTemperatureIndex = (double)Math.round((engineTemperatureIndex + (Math.random() * 5) - 2.5) * 100d) / 100d;
    }
    private double engineTemperature;

    private static double tyresPresureIndex = 2.5;
    public static double newTyresPresure() {
        return (double)Math.round((tyresPresureIndex -= (Math.random() / 50)) * 100d) / 100d;
    }
    private double tyresPresure;

    private static double oilPresureIndex = 1;
    public static double newOilPresure() {
        return (double)Math.round((oilPresureIndex += (Math.random() / 50) - 0.01) * 100d) / 100d;
    }
    private double oilPresure;

    private static int breaksTemperatureIndex = 650;
    public static int newBreaksTemperature() {
        return (breaksTemperatureIndex = (int) (breaksTemperatureIndex + ((Math.random() * 30 ) - 15)));
    }
    private  int breaksTemperature;

    private static int tyresTemperatureIndex = 85;
    public static int newTyresTemperature() {
        return (tyresTemperatureIndex += (int)((Math.random() * 6 ) - 3));
    }
    private int tyresTemperature;

    private static int tyresDegradationIndex = 100;
    public static int newTyresDegradation() {
        return (tyresDegradationIndex -= (int)(Math.random() * 5 ));
    }
    private int tyresDegradation;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    private String message;
}

