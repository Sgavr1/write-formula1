package org.example;

public class RacerParser {
    public static Racer parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Error parsing the passed string value Null");
        }

        if (line.isEmpty()) {
            return null;
        }

        String[] informationRacer = line.split("_");

        return new Racer(informationRacer[0], informationRacer[1], informationRacer[2]);
    }
}
