package org.example;

import java.util.ArrayList;
import java.util.List;

public class RacerParser {
    public List<Racer> parseList(List<String> lines) {
        List<Racer> racers = new ArrayList<>();

        lines.stream().forEach(line -> racers.add(parse(line)));
        racers.sort((a, b) -> Long.compare(a.durationBestLap(), b.durationBestLap()));

        return racers;
    }

    public Racer parse(String line){
        if (line == null) {
            throw new IllegalArgumentException("Error parsing the passed string value Null");
        }

        if (line.isEmpty()) {
            return null;
        }

        String[] informationRacer = line.split("_");

        return new Racer(informationRacer[0], informationRacer[1], informationRacer[2], informationRacer[4], informationRacer[6]);
    }
}
