package com.portfolio.spam;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        Path datasetPath = args.length > 0 ? Path.of(args[0]) : Path.of("data/messages.csv");

        try {
            List<Message> messages = new CsvDatasetLoader().load(datasetPath);
            SpamDetector detector = new SpamDetector();
            detector.train(messages);

            System.out.println("======================================");
            System.out.println("       JAVA SPAM MESSAGE DETECTOR");
            System.out.println("======================================");
            System.out.printf("Loaded %d messages | Vocabulary: %d words%n",
                    messages.size(), detector.vocabularySize());
            System.out.println("Type a message to classify it, or type 'exit' to quit.");

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("\nMessage> ");
                    if (!scanner.hasNextLine()) break;
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("exit")) break;
                    if (input.isBlank()) {
                        System.out.println("Please enter a message.");
                        continue;
                    }

                    String prediction = detector.predict(input);
                    String display = prediction.equals("spam") ? "SPAM" : "HAM (NORMAL)";
                    System.out.println("Prediction: " + display);
                    System.out.println("Note: This is a learning model; verify suspicious messages independently.");
                }
            }
        } catch (IOException exception) {
            System.err.println("Could not load dataset: " + exception.getMessage());
            System.err.println("Run this program from the project root, or pass the CSV path as an argument.");
        } catch (Exception exception) {
            System.err.println("Application error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
