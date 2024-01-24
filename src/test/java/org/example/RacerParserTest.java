package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RacerParserTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenStringIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            RacerParser.parse(null);
        });
    }

    @Test
    public void shouldReturnNullWhenStringIsEmpty() {
        assertEquals(null, RacerParser.parse(""));
    }

    @ParameterizedTest
    @CsvSource({"MES, Marcus Ericsson, SAUBER FERRARI", "LSW, Lance Stroll, WILLIAMS MERCEDES", "KMH, Kevin Magnussen, HAAS FERRARI"})
    public void shouldReturnRacerWhenCorrectString(String abbreviation, String racerName, String carName) {
        String line = abbreviation + "_" + racerName + "_" + carName;

        Racer racer = RacerParser.parse(line);

        assertEquals(abbreviation, racer.getRacerAbbreviation());
        assertEquals(racerName, racer.getNameRacer());
        assertEquals(carName, racer.getNameCar());
    }
}