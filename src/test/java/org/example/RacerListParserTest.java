package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RacerListParserTest {
    @Test
    public void shouldReturnSortedListRacerWhenCorrectString() {
        RaceFileReader fileReader = Mockito.mock(RaceFileReader.class);

        List<String> lines = new ArrayList<>(2);
        lines.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        lines.add("SVF_Sebastian Vettel_FERRARI");

        Mockito.when(fileReader.read("src/main/resources/abbreviations.txt")).thenReturn(lines);

        lines = new ArrayList<>(2);
        lines.add("DRR2018-05-24_12:14:12.054");
        lines.add("SVF2018-05-24_12:02:58.917");

        Mockito.when(fileReader.read("src/main/resources/start.log")).thenReturn(lines);

        lines = new ArrayList<>(2);
        lines.add("DRR2018-05-24_12:15:24.067");
        lines.add("SVF2018-05-24_12:04:03.332");

        Mockito.when(fileReader.read("src/main/resources/end.log")).thenReturn(lines);

        RacerListParser listParser = new RacerListParser(fileReader);

        List<Racer> sortedRacer = listParser.parseRacers();

        verify(fileReader, times(1)).read("src/main/resources/abbreviations.txt");
        verify(fileReader, times(1)).read("src/main/resources/start.log");
        verify(fileReader, times(1)).read("src/main/resources/end.log");

        assertEquals("SVF", sortedRacer.get(0).getRacerAbbreviation());
        assertEquals("DRR", sortedRacer.get(1).getRacerAbbreviation());
    }
}