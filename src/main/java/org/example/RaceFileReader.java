package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RaceFileReader implements RaceData {
    @Override
    public Map<String, Racer> getRacers() {

        try {
            Stream<String> linesAbbreviations = Files.lines(Paths.get("src/main/resources/abbreviations.txt"));
            Stream<String> lineStartRace = Files.lines(Paths.get("src/main/resources/start.log"));
            Stream<String> lineEndRace = Files.lines(Paths.get("src/main/resources/end.log"));

            RacerParser racerParser = new RacerParser();

            Map<String, Racer> racers = linesAbbreviations
                    .map(racerParser::parse)
                    .collect(Collectors.toMap(racer -> racer.getRacerAbbreviation(), racer -> racer));

            linesAbbreviations.close();

            lineStartRace
                    .forEach(line -> racers.get(line.substring(0, 3)).setStartTimeRace(LocalTime.parse(line.substring(14, line.length()))));
            lineEndRace
                    .forEach(line -> racers.get(line.substring(0, 3)).setEndTimeRace(LocalTime.parse(line.substring(14, line.length()))));

            lineStartRace.close();
            lineEndRace.close();

            return racers;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}
