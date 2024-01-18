package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RaceFileReader {
    public List<String> read(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("FileName cannot be empty or null");
        }

        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));

            return lines.toList();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
