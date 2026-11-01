package com.portfolio.spam;

import java.util.*;

/**
 * TF = term count / total terms in the document.
 * IDF = log((1 + number of documents) / (1 + document frequency)) + 1.
 */
public final class TfidfVectorizer {
    private final TextPreprocessor preprocessor;
    private final Map<String, Integer> vocabulary = new LinkedHashMap<>();
    private double[] inverseDocumentFrequencies;

    public TfidfVectorizer(TextPreprocessor preprocessor) {
        this.preprocessor = Objects.requireNonNull(preprocessor);
    }

    public void fit(List<String> documents) {
        vocabulary.clear();
        Map<String, Integer> documentFrequency = new HashMap<>();
        for (String document : documents) {
            Set<String> seen = new HashSet<>(preprocessor.tokenize(document));
            for (String token : seen) documentFrequency.merge(token, 1, Integer::sum);
        }
        List<String> terms = new ArrayList<>(documentFrequency.keySet());
        Collections.sort(terms);
        for (String term : terms) vocabulary.put(term, vocabulary.size());

        inverseDocumentFrequencies = new double[vocabulary.size()];
        for (Map.Entry<String, Integer> entry : vocabulary.entrySet()) {
            int df = documentFrequency.get(entry.getKey());
            inverseDocumentFrequencies[entry.getValue()] =
                    Math.log((1.0 + documents.size()) / (1.0 + df)) + 1.0;
        }
    }

    public double[] transform(String document) {
        if (inverseDocumentFrequencies == null) {
            throw new IllegalStateException("Call fit() before transform().");
        }
        double[] vector = new double[vocabulary.size()];
        List<String> tokens = preprocessor.tokenize(document);
        if (tokens.isEmpty()) return vector;

        Map<String, Integer> counts = new HashMap<>();
        for (String token : tokens) {
            if (vocabulary.containsKey(token)) counts.merge(token, 1, Integer::sum);
        }
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            int index = vocabulary.get(entry.getKey());
            vector[index] = (double) entry.getValue() / tokens.size()
                    * inverseDocumentFrequencies[index];
        }
        return vector;
    }

    public int vocabularySize() {
        return vocabulary.size();
    }
}
