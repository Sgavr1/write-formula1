package org.example;

import java.time.LocalTime;
import java.util.List;

public class Formula1ReportBuilder {
    private static final String PIPE_SEPARATOR = "|";
    private static final String POINT_SEPARATOR = ".";
    public String make(List<Racer> racers, int numberWinners) {
        if (racers == null) {
            throw new IllegalArgumentException("List is null");
        }

        String report = "";

        for (int i = 0; i< racers.size(); i++) {
            if (i == numberWinners) {
                report += "-".repeat(70) + "\n";
            }

            Racer racer = racers.get(i);
            String numberRacer = i + 1 + POINT_SEPARATOR;

            report += String.format("%-3s %-25s %5$s %-30s %5$s %s\n",
                    numberRacer, racer.getNameRacer(), racer.getNameCar(), LocalTime.ofNanoOfDay(racer.duration()), PIPE_SEPARATOR);
        }

        return report;
    }
}
