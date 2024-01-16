package org.example;

import java.time.LocalTime;

public class Racer {
    private String abbreviation;
    private String nameRacer;
    private String nameCar;
    private LocalTime startTimeRace;
    private LocalTime endTimeRace;

    public Racer() {
        this.abbreviation = "";
        this.nameRacer = "";
        this.nameCar = "";
        this.startTimeRace = null;
        this.endTimeRace = null;
    }

    public Racer(String abbreviation, String nameRacer, String nameCar) {
        this.abbreviation = abbreviation;
        this.nameRacer = nameRacer;
        this.nameCar = nameCar;
    }

    public Racer(String abbreviation, String nameRacer, String nameCar, LocalTime startTimeRace, LocalTime endTimeRace) {
        this.abbreviation = abbreviation;
        this.nameRacer = nameRacer;
        this.nameCar = nameCar;
        this.startTimeRace = startTimeRace;
        this.endTimeRace = endTimeRace;
    }

    public Racer(String abbreviation, String nameRacer, String nameCar, String startTime, String endTime) {
        this.abbreviation = abbreviation;
        this.nameRacer = nameRacer;
        this.nameCar = nameCar;
        startTimeRace = LocalTime.parse(startTime);
        endTimeRace = LocalTime.parse(endTime);
    }

    public String getRacerAbbreviation() {
        return abbreviation;
    }

    public String getNameCar() {
        return nameCar;
    }

    public String getNameRacer() {
        return nameRacer;
    }

    public LocalTime getStartTimeRace() {
        return startTimeRace;
    }

    public void setStartTimeRace(LocalTime startTimeRace) {
        this.startTimeRace = startTimeRace;
    }

    public LocalTime getEndTimeRace() {
        return endTimeRace;
    }

    public void setEndTimeRace(LocalTime endTimeRace) {
        this.endTimeRace = endTimeRace;
    }

    public long durationBestLap() {
        return endTimeRace.toNanoOfDay() - startTimeRace.toNanoOfDay();
    }
}