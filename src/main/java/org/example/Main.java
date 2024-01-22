package org.example;

public class Main {
    public static void main(String[] args) {
        RacerListParser parser = new RacerListParser(new RaceFileReader());
        Formula1ReportBuilder formula1ReportBuilder = new Formula1ReportBuilder();

        System.out.println(formula1ReportBuilder.make(parser.parseRacers(), 15));

    }
}