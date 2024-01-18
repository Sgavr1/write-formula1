package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceFileReaderTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileNameIsNull() {
        RaceFileReader fileReader = new RaceFileReader();

        assertThrows(IllegalArgumentException.class, () -> {
            fileReader.read(null);
        });
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileNameIsEmpty() {
        RaceFileReader fileReader = new RaceFileReader();

        assertThrows(IllegalArgumentException.class, () -> {
            fileReader.read("");
        });
    }

    @Test
    public void shouldReturnListTermsWhenCorrectFilePath() {
        RaceFileReader fileReader = new RaceFileReader();

        List<String> lines = fileReader.read("src/test/resources/test.txt");

        assertEquals(lines.get(0), "KMH_Kevin Magnussen_HAAS FERRARI");
    }
}