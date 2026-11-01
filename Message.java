package com.portfolio.spam;

public record Message(String label, String text) {
    public Message {
        if (label == null || text == null) throw new IllegalArgumentException("Label and text are required.");
        label = label.trim().toLowerCase();
        text = text.trim();
    }
}
