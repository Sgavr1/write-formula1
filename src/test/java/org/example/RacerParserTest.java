package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @CsvSource({"MES, Marcus Ericsson, SAUBER FERRARI", "LSW, Lance Stroll, WILLIAMS MERCEDES", "KMH, Kevin Magnussen, HAAS FERRARI"})
    public void shouldReturnRacerWhenCorrectString(String abbreviation, String racerName, String carName){
        RacerParser racerParser = new RacerParser();

        String line = abbreviation + "_" + racerName + "_" + carName;

        Racer racer = racerParser.parse(line);

        assertEquals(abbreviation, racer.getRacerAbbreviation());
        assertEquals(racerName, racer.getNameRacer());
        assertEquals(carName, racer.getNameCar());
    }
}