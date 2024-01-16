package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RaceFileReader {

    public List<String> read() {

        try {
            Stream<String> linesAbbreviations = Files.lines(Paths.get("src/main/resources/abbreviations.txt"));
            Stream<String> lineStartRace = Files.lines(Paths.get("src/main/resources/start.log"));
            Stream<String> lineEndRace = Files.lines(Paths.get("src/main/resources/end.log"));

            RacerParser racerParser = new RacerParser();

            Map<String, String> racers = linesAbbreviations
                    .collect(Collectors.toMap(line -> line.substring(0,3), line -> line));

            linesAbbreviations.close();

            lineStartRace.forEach(line -> {
                String key = line.substring(0,3);
                String value = racers.get(key);
                racers.put(key,value + "_" + line.substring(3,line.length()));
            });

            lineStartRace.close();

            lineEndRace.forEach(line -> {
                String key = line.substring(0,3);
                String value = racers.get(key);
                racers.put(key,value + "_" + line.substring(3,line.length()));
            });

            lineEndRace.close();

            return racers.values().stream().toList();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
