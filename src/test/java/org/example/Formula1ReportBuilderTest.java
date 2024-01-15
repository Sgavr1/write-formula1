package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Formula1ReportBuilderTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenArgumentIsNull() {
        Formula1ReportBuilder reportBuilder = new Formula1ReportBuilder();

        assertThrows(IllegalArgumentException.class, ()-> {
            reportBuilder.make(null,5);
        });
    }

    @Test
    public void shouldReturnCorrectReportWhenSortedListAndThreeWinners() {
        Formula1ReportBuilder reportBuilder = new Formula1ReportBuilder();

        List<Racer> racers = new ArrayList<>(5);
        racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", "12:14:12.054", "12:15:24.067"));
        racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", "12:02:58.917", "12:04:03.332"));
        racers.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", "12:18:20.125", "12:19:32.585"));
        racers.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", "12:03:01.250", "12:04:13.889"));
        racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", "12:00:00.000", "12:01:12.434"));

        String correctResponse = "1.  Daniel Ricciardo          | RED BULL RACING TAG HEUER      | 00:01:12.013\n" +
                "2.  Sebastian Vettel          | FERRARI                        | 00:01:04.415\n" +
                "3.  Lewis Hamilton            | MERCEDES                       | 00:01:12.460\n" +
                "-".repeat(70) +"\n"+
                "4.  Kimi Raikkonen            | FERRARI                        | 00:01:12.639\n" +
                "5.  Valtteri Bottas           | MERCEDES                       | 00:01:12.434\n";

        assertEquals(correctResponse, reportBuilder.make(racers, 3));

    }

}