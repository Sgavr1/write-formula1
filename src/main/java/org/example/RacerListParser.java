package org.example;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class RacerListParser {
    private static final String ABBREVIATIONS_FILE = "src/main/resources/abbreviations.txt";
    private static final String START_FILE = "src/main/resources/start.log";
    private static final String END_FILE = "src/main/resources/end.log";
    private final RaceFileReader fileReader;

    public RacerListParser(RaceFileReader raceFileReader) {
        fileReader = raceFileReader;
    }

    public List<Racer> parseRacers() {
        Map<String, Racer> racers = fileReader.read(ABBREVIATIONS_FILE).stream()
                .map(RacerParser::parse)
                .collect(Collectors.toMap(racer -> racer.getRacerAbbreviation(), racer -> racer));

        setTimeRaces(START_FILE, racers, (racer, time) -> racer.setStartTimeRace(time));
        setTimeRaces(END_FILE, racers, (racer, time) -> racer.setEndTimeRace(time));

        return racers.values().stream().sorted((a, b) -> Long.compare(a.durationBestLap(), b.durationBestLap())).toList();
    }

    private void setTimeRaces(String fileName, Map<String, Racer> racers, BiConsumer<Racer, LocalTime> function) {
        fileReader.read(fileName).stream()
                .forEach(line -> {
                    String key = line.substring(0, 3);
                    String strTime = line.substring(14, line.length());

                    function.accept(racers.get(key), LocalTime.parse(strTime));
                });
    }
}