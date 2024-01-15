package org.example;

public class Main {
    public static void main(String[] args) {
        RaceSorter race = new RaceSorter(new RaceFileReader());
        Formula1ReportBuilder formula1ReportBuilder = new Formula1ReportBuilder();

        System.out.println(formula1ReportBuilder.make(race.getSortedRacers(), 15));

    }
}