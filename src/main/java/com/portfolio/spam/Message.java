package com.portfolio.spam;

import java.util.Locale;

public record Message(String label, String text) {
    public Message {
        if (label == null || text == null) {
            throw new IllegalArgumentException("Label and text are required.");
        }
        label = label.trim().toLowerCase(Locale.ROOT);
        text = text.trim();
        if (!label.equals("ham") && !label.equals("spam")) {
            throw new IllegalArgumentException("Label must be 'ham' or 'spam'.");
        }
        if (text.isBlank()) {
            throw new IllegalArgumentException("Message text cannot be blank.");
        }
    }
}
