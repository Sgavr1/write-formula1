package org.example;

public class RacerParser {
    public Racer parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("String is null");
        }

        if (line.isEmpty()) {
            return null;
        }

        String[] information = line.split("_");

        return new Racer(information[0], information[1], information[2]);
    }
}
