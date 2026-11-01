package com.portfolio.spam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Converts raw text into normalized word tokens. */
public final class TextPreprocessor {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("[a-z]+");

    public List<String> tokenize(String text) {
        if (text == null || text.isBlank()) return List.of();

        String normalized = text.toLowerCase(Locale.ROOT);
        Matcher matcher = TOKEN_PATTERN.matcher(normalized);
        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            String token = matcher.group();
            if (token.length() > 1) tokens.add(token);
        }
        return tokens;
    }
}
