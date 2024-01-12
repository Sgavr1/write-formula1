package org.example;

import java.time.LocalTime;
import java.util.List;

public class Formula1ReportBuilder {
    public String make(List<Racer> racers){
        if(racers == null){
            throw new IllegalArgumentException("Argument is null");
        }

        String report = "";

        for(int i = 0; i< racers.size(); i++){
            if(i == 15){
                report += "-----------------------------------------------\n";
            }

            report += i+1 + ". " + racers.get(i).getNameRacer() + " | " + racers.get(i).getNameCar() + " | " + LocalTime.ofNanoOfDay(racers.get(i).duration()) + '\n';
        }

        return report;
    }
}
