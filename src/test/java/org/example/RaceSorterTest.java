package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RaceSorterTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCollectionIsNull() {
        RaceSorter raceSorter = new RaceSorter(null);

        assertThrows(IllegalArgumentException.class, () -> {
            raceSorter.sort(null);
        });
    }
    @Test
    public void shouldReturnSortedListWhenCollectionRacer() {
        RaceSorter raceSorter = new RaceSorter(null);

        List<Racer> racers = new ArrayList<>();
        racers.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", "12:12:01.035", "12:13:13.883"));
        racers.add(new Racer("VBM", "Valtteri Botta", "MERCEDES", "12:00:00.000", "12:01:12.434"));
        racers.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", "12:09:41.921", "12:10:54.750"));
        racers.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", "12:04:45.513", "12:05:58.778"));

        List<Racer> sortedList = raceSorter.sort(racers);

        assertEquals(sortedList.get(0).getRacerAbbreviation(), "VBM");
        assertEquals(sortedList.get(1).getRacerAbbreviation(), "CLS");
        assertEquals(sortedList.get(2).getRacerAbbreviation(), "SPF");
        assertEquals(sortedList.get(3).getRacerAbbreviation(), "MES");
    }

    @Test
    public void shouldReturnEmptyListWhenListIsEmpty() {
        RaceSorter raceSorter = new RaceSorter(null);

        assertEquals(0, raceSorter.sort(new ArrayList<>()).size());
    }

    @Test
    public void shouldUseMockWhenRunGetSortedRacers() {
        RaceData raceData = Mockito.mock(RaceData.class);

        Map<String, Racer> racers = new HashMap<>();
        racers.put("VBM", new Racer("VBM", "Valtteri Botta", "MERCEDES", "12:00:00.000", "12:01:12.434"));

        Mockito.when(raceData.getRacers()).thenReturn(racers);

        RaceSorter raceSorter = new RaceSorter(raceData);

        raceSorter.getSortedRacers();

        verify(raceData).getRacers();
    }
}