package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RacerParserTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenStringIsNull(){
        RacerParser racerParser = new RacerParser();

        assertThrows(IllegalArgumentException.class, () -> {
            racerParser.parse(null);
        });
    }

    @Test
    public void shouldReturnNullWhenStringIsEmpty(){
        RacerParser racerParser = new RacerParser();

        assertEquals(null, racerParser.parse(""));
    }

    @ParameterizedTest
    @CsvSource({"MES, Marcus Ericsson, SAUBER FERRARI, 2018-05-24_12:04:45.513, 2018-05-24_12:05:58.778", "LSW, Lance Stroll, WILLIAMS MERCEDES, 2018-05-24_12:06:13.511, 2018-05-24_12:19:32.585", "KMH, Kevin Magnussen, HAAS FERRARI, 2018-05-24_12:02:51.003, 2018-05-24_12:01:12.434"})
    public void shouldReturnRacerWhenCorrectString(String abbreviation, String racerName, String carName, String startBestLap, String endBestLap){
        RacerParser racerParser = new RacerParser();

        String line = abbreviation + "_" + racerName + "_" + carName + "_" + startBestLap + "_" + endBestLap;

        Racer racer = racerParser.parse(line);

        assertEquals(abbreviation, racer.getRacerAbbreviation());
        assertEquals(racerName, racer.getNameRacer());
        assertEquals(carName, racer.getNameCar());
        assertEquals(startBestLap.split("_")[1], racer.getStartTimeRace().toString());
        assertEquals(endBestLap.split("_")[1], racer.getEndTimeRace().toString());
    }

    @Test
    public void shouldReturnSortedListRacerWhenCorrectListString(){
        RacerParser racerParser = new RacerParser();

        List<String> lines = new ArrayList<>();
        lines.add("MES_Marcus Ericsson_SAUBER FERRARI_2018-05-24_12:04:45.513_2018-05-24_12:05:58.778");
        lines.add("LSW_Lance Stroll_WILLIAMS MERCEDES_2018-05-24_12:06:13.511_2018-05-24_12:19:32.585");
        lines.add("KMH_Kevin Magnussen_HAAS FERRARI_2018-05-24_12:02:51.003_2018-05-24_12:01:12.434");

        List<Racer> sortedRacer = racerParser.parseList(lines);

        assertEquals("KMH", sortedRacer.get(0).getRacerAbbreviation());
        assertEquals("MES", sortedRacer.get(1).getRacerAbbreviation());
        assertEquals("LSW", sortedRacer.get(2).getRacerAbbreviation());
    }
}