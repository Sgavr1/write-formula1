package org.example;

import java.util.*;

public class RaceSorter {
    private RaceData raceData;

    public RaceSorter(RaceData raceData) {
        this.raceData = raceData;
    }

    public List<Racer> getSortedRacers() {
        Map<String, Racer> racers = raceData.getRacers();

        return sort(racers.values());
    }

    public List<Racer> sort(Collection<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException("Collection is null");
        }

        return racers.stream()
                .sorted((a, b) -> Long.compare(a.duration(), b.duration()))
                .toList();
    }
}