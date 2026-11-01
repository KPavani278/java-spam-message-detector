# Learning Guide: Java Spam Message Detector

Use this guide alongside the source code. The goal is to understand each decision, not just run the application.

## Suggested 7-day plan

### Day 1 — Java and project setup
Learn:
- JDK vs JRE vs JVM.
- Classes, objects, constructors, methods.
- Packages and imports.
- What Maven does and why `pom.xml` exists.

Do:
1. Install JDK 17 and Maven.
2. Run `java -version` and `mvn -version`.
3. Open the project in IntelliJ IDEA, Eclipse, or VS Code.
4. Read `Main.java` and trace the program from `main()`.

Check yourself: What is the entry point of a Java application?

### Day 2 — Text preprocessing
Read `TextPreprocessor.java`.

Learn:
- Strings and immutability.
- Lowercasing and `Locale.ROOT`.
- Regular expressions.
- Tokenization and why consistent preprocessing matters.

Experiment:
- Add a test for punctuation, digits, uppercase text, and empty input.
- Decide whether tokens such as `I` and `a` should be kept. Explain your choice.

### Day 3 — Dataset and features
Read `Message.java`, `CsvDatasetLoader.java`, and `TfidfVectorizer.java`.

Learn:
- Labeled data: input text plus expected class.
- Vocabulary and document frequency.
- Term Frequency (TF).
- Inverse Document Frequency (IDF).
- Why machine-learning algorithms need numbers rather than raw sentences.

Formulas used here:

TF(t,d) = count(t,d) / number of tokens in d

IDF(t) = log((1 + N) / (1 + DF(t))) + 1

TF-IDF(t,d) = TF(t,d) × IDF(t)

Where N is the number of training documents and DF(t) is the number of documents containing term t.

Exercise:
- Manually calculate TF for a short sentence.
- Add several rows to `data/messages.csv`.
- Observe how the vocabulary size changes.

### Day 4 — Machine learning: Naive Bayes
Read `NaiveBayesClassifier.java`.

Learn:
- Supervised learning: examples have labels.
- Classification vs regression.
- Bayes' theorem.
- The Naive Bayes conditional-independence assumption.
- Prior probability and likelihood.
- Laplace smoothing.
- Why log probabilities are used.

Bayes' theorem:

P(class | features) = P(features | class) × P(class) / P(features)

This implementation compares class scores. It uses log prior plus weighted log feature likelihoods. The common denominator P(features) can be ignored when comparing classes because it is the same for every class.

Exercise:
- Change the smoothing constant from 1.0 to another positive value.
- Explain what smoothing protects against.

### Day 5 — Connect the pipeline
Read `SpamDetector.java` and `Main.java`.

Draw this flow:

CSV data → preprocessing → TF-IDF vectors → model training → user message → prediction

Exercise:
- Add a method that counts how many spam and ham rows are in the dataset.
- Print those counts when the program starts.

### Day 6 — Testing and evaluation
Read `SpamDetectorTest.java`.

Learn:
- Unit tests and assertions.
- Why testing only on training examples gives misleading results.
- Train/test split.
- Accuracy, precision, recall, F1-score, and confusion matrix.

Important:
- A false positive is a normal message incorrectly marked spam.
- A false negative is a spam message incorrectly marked normal.
- For spam filtering, both errors matter. Discuss the trade-off.

Exercise:
- Add at least five tests for messages the model has not seen.
- Then implement a reproducible, stratified train/test split before reporting metrics.
- Do not report accuracy from the training set as test accuracy.

### Day 7 — GitHub and project explanation
Learn:
- Repository, commit, branch, remote, push, pull.
- Why README files matter.
- How to explain architecture and trade-offs.

Do:
1. Run `mvn test`.
2. Run the application and try your own messages.
3. Update the README with a screenshot of your terminal output.
4. Push the project to GitHub.
5. Explain the project aloud in 2 minutes without reading the code.

## Important machine-learning vocabulary

| Term | Meaning |
|---|---|
| Dataset | Collection of examples used for learning/evaluation |
| Feature | Numeric property supplied to a model |
| Label | Correct category for a training example |
| Training | Learning model parameters from examples |
| Inference | Using a trained model to predict |
| Vocabulary | Unique tokens known by the vectorizer |
| TF-IDF | Weighting method for text terms |
| Classifier | Model that predicts a category |
| Overfitting | Model performs well on training data but poorly on new data |
| Generalization | Ability to work on unseen examples |

## Self-study questions

1. Why do we convert text into numbers?
2. What is the difference between TF and IDF?
3. Why is the vectorizer fitted on training data only in a proper evaluation pipeline?
4. What does Naive Bayes assume?
5. Why is Laplace smoothing useful?
6. What is the difference between precision and recall?
7. Why is a tiny dataset not enough to prove that a model is reliable?

## Next project progression

After you understand this project, extend it in this order:
1. Add a proper train/test split and evaluation metrics.
2. Use a larger public SMS spam dataset after checking its license.
3. Expose predictions through a Spring Boot REST API.
4. Build a simple frontend that calls the API.
5. Compare your implementation with Weka or Tribuo.

You are ready to move on when you can explain the full pipeline, modify the code, write tests, and discuss its limitations.
