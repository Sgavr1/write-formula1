package org.example;

import java.time.LocalTime;
import java.util.List;

public class Formula1ReportBuilder {
    private static final String PIPE_SEPARATOR = "|";
    private static final String POINT_SEPARATOR = ".";
    public String make(List<Racer> racers, int numberPlacesTop) {
        if (racers == null) {
            throw new IllegalArgumentException("Racers is null");
        }

        String report = "";

        for (int i = 0; i< racers.size(); i++) {
            if (i == numberPlacesTop) {
                report += "-".repeat(70) + "\n";
            }

            Racer racer = racers.get(i);
            String numberRacer = i + 1 + POINT_SEPARATOR;

            report += String.format("%-3s %-25s %5$s %-30s %5$s %s\n",
                    numberRacer, racer.getNameRacer(), racer.getNameCar(), LocalTime.ofNanoOfDay(racer.durationBestLap()), PIPE_SEPARATOR);
        }

        return report;
    }
}
