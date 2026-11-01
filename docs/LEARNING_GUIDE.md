# 7-Day Learning Guide

Follow this plan to understand the project and prepare to explain it in an interview.

## Day 1 — Java and Maven
- Review packages, classes, methods, records, and exception handling.
- Read `pom.xml` and understand Maven's standard source folders.
- Trace the flow from `Main` to the loader and detector.

## Day 2 — Text preprocessing
- Study `TextPreprocessor.java`.
- Understand lowercase normalization, regular expressions, and tokens.
- Try punctuation, numbers, blank input, and mixed-case messages.

## Day 3 — TF-IDF
- Learn term frequency (TF) and inverse document frequency (IDF).
- Trace how the vocabulary is built and how a message becomes a vector.
- Explain why common words receive a lower IDF weight.

## Day 4 — Naive Bayes
- Learn Bayes' theorem, class priors, and conditional probabilities.
- Understand the conditional-independence assumption.
- Find the Laplace smoothing calculation.
- Explain why the implementation adds log probabilities.

## Day 5 — Testing
- Run `mvn test`.
- Read each JUnit test and identify the behavior it checks.
- Add tests for empty input, unknown vocabulary, and malformed CSV.

## Day 6 — Evaluation
- Learn precision, recall, F1-score, and the confusion matrix.
- Use a larger, properly sourced dataset.
- Keep training and test examples separate; never report training-set results as test accuracy.

## Day 7 — Explain and extend
Practice describing the data flow, feature extraction, classifier, tests, and limitations. Then choose one improvement, implement it, and add tests.

## Useful commands

```bash
mvn test
mvn compile
mvn exec:java
```

**Important:** The bundled dataset is a tiny synthetic teaching sample. It is not representative of real SMS traffic, and the model is not production-ready.
