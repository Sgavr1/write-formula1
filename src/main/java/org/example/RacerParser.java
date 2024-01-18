package org.example;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerParser {
    private static final String ABBREVIATIONS_FILE = "src/main/resources/abbreviations.txt";
    private static final String START_FILE = "src/main/resources/start.log";
    private static final String END_FILE = "src/main/resources/end.log";
    private RaceFileReader fileReader;

    public RacerParser() {
        fileReader = new RaceFileReader();
    }

    public List<Racer> parseRacers() {
        Map<String, Racer> racers = fileReader.read(ABBREVIATIONS_FILE).stream()
                .map(this::parse)
                .collect(Collectors.toMap(racer -> racer.getRacerAbbreviation(), racer -> racer));

        fileReader.read(START_FILE).stream()
                .forEach(line -> racers.get(line.substring(0, 3)).setStartTimeRace(LocalTime.parse(line.substring(14, line.length()))));

        fileReader.read(END_FILE).stream()
                .forEach(line -> racers.get(line.substring(0, 3)).setEndTimeRace(LocalTime.parse(line.substring(14, line.length()))));

        return racers.values().stream().sorted((a, b) -> Long.compare(a.durationBestLap(), b.durationBestLap())).toList();
    }

    public Racer parse(String line) {
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
