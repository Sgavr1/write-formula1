package org.example;

public class Main {
    public static void main(String[] args) {
        RacerParser parser = new RacerParser();
        Formula1ReportBuilder formula1ReportBuilder = new Formula1ReportBuilder();

        System.out.println(formula1ReportBuilder.make(parser.parseRacers(), 15));

    }
}