package com.portfolio.spam;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Loads a two-column CSV with a header: label,message. */
public final class CsvDatasetLoader {
    public List<Message> load(Path path) throws IOException {
        List<Message> messages = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1 && line.toLowerCase(Locale.ROOT).startsWith("label,")) continue;
                if (line.isBlank()) continue;
                List<String> columns = parseCsvLine(line);
                if (columns.size() < 2) {
                    throw new IOException("Invalid CSV row at line " + lineNumber + ": expected label,message");
                }
                try {
                    messages.add(new Message(columns.get(0),
                            String.join(",", columns.subList(1, columns.size()))));
                } catch (IllegalArgumentException exception) {
                    throw new IOException("Invalid message at line " + lineNumber + ": " + exception.getMessage(), exception);
                }
            }
        }
        if (messages.isEmpty()) throw new IOException("Dataset is empty: " + path);
        return messages;
    }

    // Supports quoted values and escaped double quotes in a single-line CSV record.
    private List<String> parseCsvLine(String line) {
        List<String> columns = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    value.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                columns.add(value.toString());
                value.setLength(0);
            } else {
                value.append(ch);
            }
        }
        columns.add(value.toString());
        return columns;
    }
}
