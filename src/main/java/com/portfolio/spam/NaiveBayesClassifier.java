package com.portfolio.spam;

import java.util.*;

/** Multinomial Naive Bayes using non-negative TF-IDF features and Laplace smoothing. */
public final class NaiveBayesClassifier {
    private final List<String> labels;
    private final Map<String, double[]> featureTotals = new LinkedHashMap<>();
    private final Map<String, Integer> documentCounts = new LinkedHashMap<>();
    private int featureCount;
    private int totalDocuments;

    public NaiveBayesClassifier(List<String> labels) {
        this.labels = List.copyOf(labels);
        for (String label : labels) {
            featureTotals.put(label, null);
            documentCounts.put(label, 0);
        }
    }

    public void fit(List<double[]> vectors, List<String> targetLabels) {
        if (vectors.size() != targetLabels.size() || vectors.isEmpty()) {
            throw new IllegalArgumentException("Training vectors and labels must have equal, non-zero size.");
        }
        featureCount = vectors.get(0).length;
        for (String label : labels) featureTotals.put(label, new double[featureCount]);
        for (String label : labels) documentCounts.put(label, 0);
        totalDocuments = vectors.size();

        for (int row = 0; row < vectors.size(); row++) {
            double[] vector = vectors.get(row);
            String label = targetLabels.get(row);
            if (!featureTotals.containsKey(label)) throw new IllegalArgumentException("Unknown label: " + label);
            if (vector.length != featureCount) throw new IllegalArgumentException("Inconsistent feature vector length.");
            documentCounts.merge(label, 1, Integer::sum);
            double[] totals = featureTotals.get(label);
            for (int col = 0; col < featureCount; col++) {
                if (!Double.isFinite(vector[col]) || vector[col] < 0) {
                    throw new IllegalArgumentException("Features must be finite and non-negative.");
                }
                totals[col] += vector[col];
            }
        }
    }

    public String predict(double[] vector) {
        if (totalDocuments == 0) throw new IllegalStateException("Train the classifier before predicting.");
        if (vector.length != featureCount) throw new IllegalArgumentException("Unexpected feature vector size.");

        String bestLabel = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (String label : labels) {
            int classDocuments = documentCounts.get(label);
            if (classDocuments == 0) continue;
            double[] totals = featureTotals.get(label);
            double denominator = Arrays.stream(totals).sum() + featureCount;
            double score = Math.log((double) classDocuments / totalDocuments);
            for (int i = 0; i < featureCount; i++) {
                if (vector[i] > 0) {
                    double probability = (totals[i] + 1.0) / denominator;
                    score += vector[i] * Math.log(probability);
                }
            }
            if (score > bestScore) {
                bestScore = score;
                bestLabel = label;
            }
        }
        return bestLabel;
    }
}
