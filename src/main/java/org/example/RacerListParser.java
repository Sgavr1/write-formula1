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
        RacerParser parser = new RacerParser();
        Map<String, Racer> racers = fileReader.read(ABBREVIATIONS_FILE).stream()
                .map(parser::parse)
                .collect(Collectors.toMap(racer -> racer.getRacerAbbreviation(), racer -> racer));

        setTimeRace(START_FILE, racers, (racer, time) -> racer.setStartTimeRace(time));
        setTimeRace(END_FILE, racers, (racer, time) -> racer.setEndTimeRace(time));

        return racers.values().stream().sorted((a, b) -> Long.compare(a.durationBestLap(), b.durationBestLap())).toList();
    }

    private void setTimeRace(String fileName, Map<String, Racer> racer, BiConsumer<Racer, LocalTime> function) {
        fileReader.read(fileName).stream()
                .forEach(line -> {
                    String key = line.substring(0, 3);
                    String strTime = line.substring(14, line.length());

                    function.accept(racer.get(key), LocalTime.parse(strTime));
                });
    }
}