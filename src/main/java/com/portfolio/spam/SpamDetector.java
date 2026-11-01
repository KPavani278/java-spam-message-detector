package com.portfolio.spam;

import java.util.List;
import java.util.stream.Collectors;

public final class SpamDetector {
    private final TextPreprocessor preprocessor = new TextPreprocessor();
    private final TfidfVectorizer vectorizer = new TfidfVectorizer(preprocessor);
    private final NaiveBayesClassifier classifier =
            new NaiveBayesClassifier(List.of("ham", "spam"));

    public void train(List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            throw new IllegalArgumentException("Training messages cannot be empty.");
        }
        List<String> texts = messages.stream().map(Message::text).collect(Collectors.toList());
        List<String> labels = messages.stream().map(Message::label).collect(Collectors.toList());
        vectorizer.fit(texts);
        List<double[]> vectors = texts.stream().map(vectorizer::transform).collect(Collectors.toList());
        classifier.fit(vectors, labels);
    }

    public String predict(String text) {
        return classifier.predict(vectorizer.transform(text));
    }

    public int vocabularySize() {
        return vectorizer.vocabularySize();
    }
}
