package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try {
            Stream<String> linesAbbreviations = Files.lines(Paths.get("src/main/resources/abbreviations.txt"));
            Stream<String> lineStartRace = Files.lines(Paths.get("src/main/resources/start.log"));
            Stream<String> lineEndRace = Files.lines(Paths.get("src/main/resources/end.log"));

            Map<String,Racer> racers = linesAbbreviations.map(Racer::parse).collect(Collectors.toMap(racer -> racer.getRacerAbbreviation(), racer -> racer));

            lineStartRace.forEach(line -> racers.get(line.substring(0,3)).setStartTimeRace(LocalTime.parse(line.substring(14,line.length()))));
            lineEndRace.forEach(line -> racers.get(line.substring(0,3)).setEndTimeRace(LocalTime.parse(line.substring(14,line.length()))));

            List<Racer> sortRacers = racers.values().stream().sorted((a,b) -> Long.compare(a.duration(), b.duration())).toList();

            Formula1ReportBuilder formula1ReportBuilder = new Formula1ReportBuilder();

            System.out.println(formula1ReportBuilder.make(sortRacers));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}