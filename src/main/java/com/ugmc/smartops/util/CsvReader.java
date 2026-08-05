package com.ugmc.smartops.util;

import com.ugmc.smartops.datastructure.DynamicArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A lightweight CSV reader that parses comma-separated values into rows of
 * strings. Handles simple quoted fields containing commas.
 *
 * @author UGMC Smart Operations Team
 */
public final class CsvReader {

    private CsvReader() {
    }

    /**
     * Reads a CSV file and returns all rows (excluding the header row).
     *
     * @param path     path to the CSV file
     * @param hasHeader whether the first line is a header to skip
     * @return a DynamicArray of rows, each row a DynamicArray of field strings
     * @throws IOException if the file cannot be read
     */
    public static DynamicArray<DynamicArray<String>> readAll(String path, boolean hasHeader)
            throws IOException {
        DynamicArray<DynamicArray<String>> rows = new DynamicArray<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                if (firstLine && hasHeader) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;
                rows.add(parseLine(line));
            }
        }
        return rows;
    }

    /**
     * Parses a single CSV line into fields, respecting double-quoted fields.
     */
    public static DynamicArray<String> parseLine(String line) {
        DynamicArray<String> fields = new DynamicArray<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"'); // escaped quote
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    current.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    fields.add(current.toString());
                    current.setLength(0);
                } else {
                    current.append(c);
                }
            }
        }
        fields.add(current.toString());
        return fields;
    }
}
